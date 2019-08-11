package pt.isel.vsddashboardapplication.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import pt.isel.vsddashboardapplication.model.Alarm
import pt.isel.vsddashboardapplication.model.VRS
import pt.isel.vsddashboardapplication.model.VSC
import pt.isel.vsddashboardapplication.repository.base.VrsRepository
import pt.isel.vsddashboardapplication.repository.base.VscRepository
import pt.isel.vsddashboardapplication.utils.RefreshState
import pt.isel.vsddashboardapplication.viewmodel.base.BaseViewModel
import pt.isel.vsddashboardapplication.viewmodel.parent.AlarmParentViewModel
import javax.inject.Inject

class VscViewModel @Inject constructor(
    private val repository: VscRepository,
    private val vrsRepository: VrsRepository
) : BaseViewModel<VSC>(), AlarmParentViewModel {

    private val alarmLiveData = MediatorLiveData<List<Alarm>?>()
    val vrsLiveData = MediatorLiveData<List<VRS>?>()

    override fun getRefreshState(): LiveData<RefreshState> = this.refreshStateLiveData

    override suspend fun setLiveData() {
        liveData.addSource(repository.get(id)){ liveData.value = it }
        if(liveData.value == null) {
            repository.update(id)
            repository.updateAlarms(id)
            vrsRepository.updateAll(id)
        }

        alarmLiveData.addSource(Transformations.switchMap(liveData){ vsc ->
            repository.getAlarms(vsc.iD)
        }){
            alarmLiveData.value = it
        }
    }

    override fun getAlarmsLiveData(): LiveData<List<Alarm>?> {
        return alarmLiveData
    }

    override fun updateAlarmsLiveData() {
        viewModelScope.launch {
            refreshStateLiveData.postValue(RefreshState.INPROGRESS)
            repository.updateAlarms(id) { refreshStateLiveData.postValue(RefreshState.NONE) }
        }
    }

    fun updateVrss() {
        viewModelScope.launch {
            refreshStateLiveData.postValue(RefreshState.INPROGRESS)
            vrsRepository.updateAll(id) { refreshStateLiveData.postValue(RefreshState.NONE) }
        }
    }

    /**
     * Updates live data
     */
    override suspend fun updateLiveData() {
        refreshStateLiveData.postValue(RefreshState.INPROGRESS)
        repository.update(id) { refreshStateLiveData.postValue(RefreshState.NONE) }
    }

    private lateinit var id: String

    /**
     * Initiates the view model
     * @param id: the VSC ID
     */
    fun init(id: String) {
        this.id = id
        viewModelScope.launch {
            setLiveData()
            liveData.value ?: updateLiveData()
        }
    }

}