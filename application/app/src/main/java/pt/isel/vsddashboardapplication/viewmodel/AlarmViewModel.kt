package pt.isel.vsddashboardapplication.viewmodel

import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import pt.isel.vsddashboardapplication.repository.AlarmRepository
import pt.isel.vsddashboardapplication.model.Alarm

/**
 * View Model responsible for interacting with a list of alarms
 */
class AlarmViewModel : BaseViewModel<List<Alarm>>() {

    private lateinit var repository: AlarmRepository
    private lateinit var nsgId: String

    override suspend fun setLiveData() {
        val newVal = repository.getAlarmForNSG(nsgId)
        this.liveData.addSource(newVal) { this.liveData.value = it }
    }

    override suspend fun updateLiveData() { repository.updateAll(nsgId) }

    /**
     * Sets the alarm repository and the NSG ID
     */
    fun init(repo: AlarmRepository, nsgId: String) {
        this.repository = repo
        this.nsgId = nsgId
        viewModelScope.launch { setLiveData() }
    }


}