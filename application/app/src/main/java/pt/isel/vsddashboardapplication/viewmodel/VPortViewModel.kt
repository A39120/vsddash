package pt.isel.vsddashboardapplication.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import pt.isel.vsddashboardapplication.model.Alarm
import pt.isel.vsddashboardapplication.model.VPort
import pt.isel.vsddashboardapplication.model.VRS
import pt.isel.vsddashboardapplication.repository.base.VPortRepository
import pt.isel.vsddashboardapplication.repository.base.VrsRepository
import pt.isel.vsddashboardapplication.utils.RefreshState
import pt.isel.vsddashboardapplication.viewmodel.base.BaseViewModel
import pt.isel.vsddashboardapplication.viewmodel.parent.AlarmParentViewModel
import javax.inject.Inject

class VPortViewModel @Inject constructor(
    private val repository: VPortRepository,
    private val vrsRepository: VrsRepository
) : BaseViewModel<VPort>(), AlarmParentViewModel {

    override fun getAlarmsLiveData(): LiveData<List<Alarm>?> {
        return this.alarmsLiveData
    }

    override fun getRefreshState(): LiveData<RefreshState> = this.refreshStateLiveData

    override fun updateAlarmsLiveData() {
        viewModelScope.launch {
            refreshStateLiveData.postValue(RefreshState.INPROGRESS)
            repository.updateAlarms(portId?:liveData.value!!.iD) { refreshStateLiveData.postValue(RefreshState.NONE) }
        }
    }

    private var portId: String? = null
    private var vrsId: String? = null

    val vrsLiveData = MediatorLiveData<VRS>()
    val alarmsLiveData = MediatorLiveData<List<Alarm>?>()

    override suspend fun setLiveData() {
        vrsId?.let { vrsId -> liveData.addSource(vrsRepository.get(vrsId)) { vrsLiveData.value = it } }
        portId?.let { portId ->
            liveData.addSource(repository.get(portId)) { port -> liveData.value = port}
            if(liveData.value == null)
                repository.get(portId)

            alarmsLiveData.addSource(Transformations.switchMap(liveData) { repository.getAlarms(it.iD) }) {
                alarmsLiveData.value = it
            }
        }
    }

    override suspend fun updateLiveData() {
        portId?.let {
            this.refreshStateLiveData.postValue(RefreshState.INPROGRESS)
            repository.update(it) { this.refreshStateLiveData.postValue(RefreshState.NONE) }
        }
    }

    fun init(portId: String? = null, vrsId: String? = null){
        this.portId = portId
        this.vrsId = vrsId
        viewModelScope.launch { setLiveData() }
    }

}