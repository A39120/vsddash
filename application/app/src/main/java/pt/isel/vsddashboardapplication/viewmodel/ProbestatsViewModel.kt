package pt.isel.vsddashboardapplication.viewmodel

import android.util.Log
import androidx.lifecycle.*
import kotlinx.coroutines.launch
import pt.isel.vsddashboardapplication.model.NSGInfo
import pt.isel.vsddashboardapplication.model.NSPort
import pt.isel.vsddashboardapplication.model.statistics.DpiProbestats
import pt.isel.vsddashboardapplication.repository.base.DpiProbestatsRepository
import pt.isel.vsddashboardapplication.repository.base.NSGinfoRepository
import pt.isel.vsddashboardapplication.repository.base.NSPortRepository
import pt.isel.vsddashboardapplication.utils.DateRange
import pt.isel.vsddashboardapplication.utils.TimeRangeCalculator
import javax.inject.Inject

/**
 * The view model responsible for getting the probe stats
 */
class ProbestatsViewModel @Inject constructor(
    private val repository: DpiProbestatsRepository,
    private val nsgRepository: NSGinfoRepository,
    private val NSPortRepository: NSPortRepository
)
    : ViewModel() {
    companion object{
        private const val TAG = "VM/PROBE_STATS"
    }

    val inbound =  MediatorLiveData<List<DpiProbestats>>()
    val outbound =  MediatorLiveData<List<DpiProbestats>>()

    private val nsgLiveData = MediatorLiveData<NSGInfo>()
    private val portLiveData = MediatorLiveData<NSPort>()

    private val range = MutableLiveData<DateRange>()

    private var port: String? = null
    private var nsg: String? = null
    private var apm: String? = null

    private suspend fun setLiveData()  {
        Log.d(TAG, "Setting liveData of probe list - Port: $port and NSG: $nsg)")
        nsgLiveData.addSource(nsgRepository.get(nsg!!)) { nsgLiveData.value = it }
        portLiveData.addSource(NSPortRepository.get(port!!)) { portLiveData.value = it }

        inbound.addSource(Transformations.switchMap(nsgLiveData) { nsg ->
            Transformations.switchMap(portLiveData) { port ->
                Transformations.switchMap(range) {
                    range ->
                        repository.getInbound(port.physicalName ?: "", nsg.name ?: "", apm, range.start, range.end)
                }
            }
        }) {stats -> inbound.value = stats}

        outbound.addSource(Transformations.switchMap(nsgLiveData) { nsg ->
            Transformations.switchMap(portLiveData) { port ->
                Transformations.switchMap(range) {
                        range ->
                        repository.getOutbound(port.physicalName ?: "", nsg.name ?: "", apm, range.start, range.end)
                }
            }
        }) { stats -> outbound.value = stats}

    }

    fun updateLiveData() {
        viewModelScope.launch {
            Log.d(TAG, "Updating liveData from ${range.value?.start} to ${range.value?.end}")
            val portName = portLiveData.value?.physicalName ?: ""
            val system = nsgLiveData.value?.name ?: ""

            Log.d(TAG, "Updating for port $portName of NSG $system")
            repository.updateInbound(portName, system, apm, range.value?.start, range.value?.end)
            repository.updateOutbound(portName, system, apm, range.value?.start, range.value?.end)
        }
    }

    /**
     * Sets boundaries for current LiveData;
     * @param min: Minimum timestamp of list
     * @param max: Maximum timestamp of list
     */
    fun setBoundaries(min: Long? = null, max: Long? = null){
        val nrange = if(min == null){
            if(max == null)
                TimeRangeCalculator.getLastDayRange()
            else
                TimeRangeCalculator.getHourBefore(max)
        } else {
            if(max == null)
                TimeRangeCalculator.getHourAfter(min)
            else
                TimeRangeCalculator.getCustomRange(min, max)
        }

        range.postValue(nrange)
    }

    fun init(port: String?, nsg: String?) {
        this.port = port
        this.nsg = nsg
        setBoundaries()
        viewModelScope.launch { setLiveData() }
    }

}