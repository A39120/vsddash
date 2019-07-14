package pt.isel.vsddashboardapplication.viewmodel

import android.util.Log
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import pt.isel.vsddashboardapplication.repository.AlarmRepository
import pt.isel.vsddashboardapplication.model.Alarm
import pt.isel.vsddashboardapplication.utils.RefreshState
import pt.isel.vsddashboardapplication.viewmodel.base.BaseListViewModel
import pt.isel.vsddashboardapplication.viewmodel.base.BaseViewModel
import javax.inject.Inject

/**
 * View Model responsible for interacting with a list of alarms
 */
class AlarmViewModel @Inject constructor(private val repository: AlarmRepository): BaseListViewModel<Alarm>() {
    private companion object {
        const val TAG = "VM/ALARM_LIST"
    }

    private lateinit var nsgId: String

    override suspend fun setLiveData() {
        Log.d(TAG, "Setting livedata with all alarms for NSG $nsgId (repository = ${repository.javaClass}")
        repository.let { repo ->
            val newVal = repo.getAll(nsgId)
            this.liveData.addSource(newVal) { this.liveData.value = it }
        }
    }

    override suspend fun updateLiveData() {
        Log.d(TAG, "Updating livedata with all alarms for NSG $nsgId (repository = ${repository.javaClass}")
        this.refreshStateLiveData.postValue(RefreshState.INPROGRESS)
        repository.updateAll(nsgId) { this.refreshStateLiveData.postValue(RefreshState.NONE)}
    }

    /**
     * Sets the alarm repository and the NSG ID
     */
    fun init(nsgId: String) {
        Log.d(TAG, "Setting NSG Id: $nsgId")
        this.nsgId = nsgId
        viewModelScope.launch { setLiveData() }
    }

}