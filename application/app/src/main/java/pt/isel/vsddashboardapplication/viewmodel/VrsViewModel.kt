package pt.isel.vsddashboardapplication.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import pt.isel.vsddashboardapplication.model.Alarm
import pt.isel.vsddashboardapplication.model.VPort
import pt.isel.vsddashboardapplication.model.VRS
import pt.isel.vsddashboardapplication.repository.base.VrsRepository
import pt.isel.vsddashboardapplication.utils.RefreshState
import pt.isel.vsddashboardapplication.viewmodel.base.BaseViewModel
import pt.isel.vsddashboardapplication.viewmodel.parent.AlarmParentViewModel
import javax.inject.Inject

class VrsViewModel @Inject constructor(
    private val repository: VrsRepository
) : BaseViewModel<VRS>(), AlarmParentViewModel {

    val vportLiveData = MediatorLiveData<List<VPort>?>()

    private val alarmsLiveData = MediatorLiveData<List<Alarm>?>()
    private val alarmRefreshStateLiveData = MediatorLiveData<RefreshState>()

    override fun getRefreshState(): LiveData<RefreshState> = this.alarmRefreshStateLiveData

    override fun getAlarmsLiveData(): LiveData<List<Alarm>?> = alarmsLiveData

    override fun updateAlarmsLiveData() {
        viewModelScope.launch {
            alarmRefreshStateLiveData.postValue(RefreshState.INPROGRESS)
            repository.updateAlarms(id) { alarmRefreshStateLiveData.postValue(RefreshState.NONE) }
        }
    }

    fun updateVportLiveData() {
        viewModelScope.launch { repository.updateVports(id) }
    }

    override suspend fun setLiveData() {
        liveData.addSource(repository.get(id)){ liveData.value = it }
        if(liveData.value == null)
            repository.update(id)

        alarmsLiveData.addSource(Transformations.switchMap(liveData){
            val ld = repository.getAlarms(it.iD)
            ld.value ?: viewModelScope.launch { repository.updateAlarms(it.iD) }
            ld
        }) { alarmsLiveData.value = it }


        vportLiveData.addSource(Transformations.switchMap(liveData){
            val ld = repository.getVports(it.iD)
            ld.value ?: viewModelScope.launch { repository.updateVports(it.iD) }
            ld
        }) { vportLiveData.value = it }
    }

    override suspend fun updateLiveData() {
        refreshStateLiveData.postValue(RefreshState.INPROGRESS)
        repository.update(id) { refreshStateLiveData.postValue(RefreshState.NONE) }
    }

    private lateinit var id: String

    fun init(id: String) {
        this.id = id
        viewModelScope.launch { setLiveData() }
    }

}
