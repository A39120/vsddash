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
) : ViewModel() {
    companion object{ private const val TAG = "VM/PROBE_STATS" }

    val inbound =  MediatorLiveData<List<DpiProbestats>>()
    val outbound =  MediatorLiveData<List<DpiProbestats>>()

    private lateinit var range : DateRange

    private var port: String? = null
    private var nsg: String? = null
    private var apm: String? = null
    private var pf: String? = null

    private suspend fun setLiveData()  {
        Log.d(TAG, "Setting liveData of probe list - Port: $port and NSG: $nsg)")

        inbound.addSource(repository.getInbound( port ?: "", nsg ?: "", apm, range.start, range.end ))
            {stats -> inbound.postValue(stats)}

        if(inbound.value == null)
            repository.updateInbound( port = port ?: "", nsg = nsg ?: "", apm = apm, start = range.start, end = range.end )


        outbound.addSource( repository.getOutbound( port ?: "", nsg ?: "", apm, range.start, range.end ))
            { stats -> outbound.postValue(stats)}

        if(outbound.value == null)
            repository.updateOutbound(port?: "", nsg?: "", apm, range.start, range.end)

    }

    fun updateLiveData() {
        viewModelScope.launch {
            Log.d(TAG, "Updating for port $port of NSG $nsg")
            if(port != null && nsg != null) {
                repository.updateInbound(port!!, nsg!!, apm, range.start, range.end)
                repository.updateOutbound(port!!, nsg!!, apm, range.start, range.end)
            }
        }
    }

    /**
     * Sets boundaries for current LiveData;
     * @param min: Minimum timestamp of list
     * @param max: Maximum timestamp of list
     */
    fun setBoundaries(min: Long? = null, max: Long? = null){
        range = if(min == null){
            if(max == null)
                TimeRangeCalculator.getLast5Minutes()
            else
                TimeRangeCalculator.getHourBefore(max)
        } else {
            if(max == null)
                TimeRangeCalculator.getHourAfter(min)
            else
                TimeRangeCalculator.getCustomRange(min, max)
        }
    }

    fun init(port: String?, nsg: String?, apm: String? = null, pf: String? = null) {
        this.port = port
        this.nsg = nsg
        this.apm = apm
        this.pf = pf

        setBoundaries()
        viewModelScope.launch { setLiveData() }
    }

}