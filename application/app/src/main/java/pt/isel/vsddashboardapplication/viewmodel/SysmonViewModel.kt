package pt.isel.vsddashboardapplication.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import pt.isel.vsddashboardapplication.model.statistics.Sysmon
import pt.isel.vsddashboardapplication.repository.base.SysmonRepository
import pt.isel.vsddashboardapplication.utils.TimeRangeCalculator
import pt.isel.vsddashboardapplication.viewmodel.base.BaseListViewModel
import javax.inject.Inject

class SysmonViewModel @Inject constructor(
    private val repository: SysmonRepository
): BaseListViewModel<Sysmon>() {
    companion object {
        private const val TAG = "VM/SYSMON"
    }

    private val query = MutableLiveData<SystemVmData>()

    /**
     * Sets up live data
     */
    override suspend fun setLiveData() {
        Log.d(TAG, "Setting up view model")
        liveData.addSource(
            repository.get(query.value!!.systemId,
                query.value!!.from,
                query.value!!.to)
        ){
            liveData.value = it
        }
        if(liveData.value == null)
            viewModelScope.launch { updateLiveData() }

    }

    /**
     * Updates the live data
     */
    override suspend fun updateLiveData() {
        Log.d(TAG, "Updating view model")
        query.value?.run { repository.update(systemId, from, to) }
    }

    /**
     * Sets up the system monitor model;
     *
     * @param systemId: the id of the system
     * @param from: the start date
     * @param to: the end date
     */
    fun init(systemId: String, from: Long? = null, to: Long? = null ) {
        val range = TimeRangeCalculator.getDefaultCustomRange(from, to)
        val data = SystemVmData().apply {
            this.systemId = systemId
            this.from = range.start
            this.to = range.end
        }

        if(data.systemId != query.value?.systemId ||
            data.from != query.value?.from ||
            data.to != query.value?.to)
                query.value = data

        viewModelScope.launch{ setLiveData() }
    }

    private inner class SystemVmData {
        lateinit var systemId : String
        var from: Long? = 0
        var to: Long? = 0
    }

}