package pt.isel.vsddashboardapplication.viewmodel

import android.util.Log
import androidx.lifecycle.*
import kotlinx.coroutines.launch
import pt.isel.vsddashboardapplication.model.NSGInfo
import pt.isel.vsddashboardapplication.model.NSPort
import pt.isel.vsddashboardapplication.model.statistics.DpiProbestats
import pt.isel.vsddashboardapplication.repository.DpiProbestatsRepository
import pt.isel.vsddashboardapplication.repository.NSGinfoRepository
import pt.isel.vsddashboardapplication.repository.PortRepository
import javax.inject.Inject

/**
 * The view model responsible for getting the probe stats
 */
class ProbestatsViewModel @Inject constructor(
    private val repository: DpiProbestatsRepository,
    private val nsgRepository: NSGinfoRepository,
    private val portRepository: PortRepository
)
    : ViewModel() {
    companion object{
        private const val TAG = "VM/PROBE_STATS"
    }

    val inbound =  MediatorLiveData<List<DpiProbestats>>()
    val outbound =  MediatorLiveData<List<DpiProbestats>>()

    private val nsgLiveData = MediatorLiveData<NSGInfo>()
    private val portLiveData = MediatorLiveData<NSPort>()


    private var port: String? = null
    private var nsg: String? = null
    private var apm: String? = null
    private var minimum: Long? = null
    private var maximum: Long? = null

    private suspend fun setLiveData()  {
        Log.d(TAG, "Setting liveData of probe list - Port: $port and NSG: $nsg)")

        nsgLiveData.addSource(nsgRepository.get(nsg!!)) { nsgLiveData.value = it }
        portLiveData.addSource(portRepository.get(port!!)) { portLiveData.value = it }

        inbound.addSource(Transformations.switchMap(nsgLiveData) { nsg ->
            Transformations.switchMap(portLiveData) { port ->
                    repository.getInbound(port.physicalName ?: "", nsg.name ?: "", apm, minimum, maximum)
            }
        }) { inbound.value = it }

        outbound.addSource(Transformations.switchMap(nsgLiveData) { nsg ->
            Transformations.switchMap(portLiveData) { port ->
                repository.getOutbound(port.physicalName ?: "", nsg.name ?: "", apm, minimum, maximum)
            }
        }) { outbound.value = it }

        /*
        repository.let { repo ->
            this.inbound.addSource(repo.getInbound(port!!, nsg!!, apm, minimum!!, maximum!!)) { inbound.value = it }
            this.outbound.addSource(repo.getOutbound(port!!, nsg!!, apm, minimum!!, maximum!!)) { outbound.value = it }
        }
        */
    }

    fun updateLiveData() {
        viewModelScope.launch {
            Log.d(TAG, "Updating liveData from $minimum to $maximum")
            val portName = portLiveData.value?.physicalName ?: ""
            val system = nsgLiveData.value?.name ?: ""

            Log.d(TAG, "Updating for port $portName of NSG $system")
            repository.updateInbound(portName, system, apm, minimum, maximum)
            repository.updateOutbound(portName, system, apm, minimum, maximum)
        }
    }

    /**
     * Sets boundaries for current LiveData;
     * @param min: Minimum timestamp of list
     * @param max: Maximum timestamp of list
     */
    private fun setBoundaries(min: Long? = null, max: Long? = null){
        this.maximum = max ?: System.currentTimeMillis()
        this.minimum = min ?: this.maximum!! - 3600 * 1000
    }

    fun init(port: String, nsg: String) {
        this.port = port
        this.nsg = nsg
        setBoundaries()
        viewModelScope.launch { setLiveData() }
    }

}