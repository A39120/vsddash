package pt.isel.vsddashboardapplication.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import pt.isel.vsddashboardapplication.model.Alarm
import pt.isel.vsddashboardapplication.model.NSGateway
import pt.isel.vsddashboardapplication.model.VPort
import pt.isel.vsddashboardapplication.model.VRS
import pt.isel.vsddashboardapplication.repository.base.NSGatewayRepository
import pt.isel.vsddashboardapplication.repository.base.VPortRepository
import pt.isel.vsddashboardapplication.repository.base.VrsRepository
import pt.isel.vsddashboardapplication.utils.RefreshState
import pt.isel.vsddashboardapplication.viewmodel.base.BaseViewModel
import pt.isel.vsddashboardapplication.viewmodel.parent.AlarmParentViewModel
import javax.inject.Inject

class VPortViewModel @Inject constructor(
    private val repository: VPortRepository,
    private val vrsRepository: VrsRepository,
    private val gatewayRepository: NSGatewayRepository
) : BaseViewModel<VPort>(), AlarmParentViewModel {

    private var portId: String? = null
    private var vrsId: String? = null

    val vrsLiveData = MediatorLiveData<VRS>()

    private val alarmsLiveData = MediatorLiveData<List<Alarm>?>()
    private val alarmRefreshStateLiveData = MediatorLiveData<RefreshState>()
    val gatewaysLiveData = MediatorLiveData<NSGateway?>()

    override fun getAlarmsLiveData(): LiveData<List<Alarm>?> = this.alarmsLiveData
    override fun getRefreshState(): LiveData<RefreshState> = this.alarmRefreshStateLiveData

    override fun updateAlarmsLiveData() {
        viewModelScope.launch {
            alarmRefreshStateLiveData.postValue(RefreshState.INPROGRESS)
            repository.updateAlarms(portId?:liveData.value!!.iD) { alarmRefreshStateLiveData.postValue(RefreshState.NONE) }
        }
    }

    override suspend fun setLiveData() {
        vrsId?.let { vrsId -> liveData.addSource(vrsRepository.get(vrsId)) { vrsLiveData.value = it } }
        portId?.let { portId ->
            liveData.addSource(repository.get(portId)) { port -> liveData.value = port}
            if(liveData.value == null)
                repository.get(portId)

            alarmsLiveData.addSource(Transformations.switchMap(liveData) { repository.getAlarms(it.iD) }) {
                alarmsLiveData.value = it
            }

            gatewaysLiveData.addSource(Transformations.switchMap(liveData) {
                it.associatedGatewayID?.let { gateway ->
                    val ld = gatewayRepository.get(gateway)
                    if(ld.value == null)
                        viewModelScope.launch { gatewayRepository.updateNsgInfo(gateway) }
                    ld
                }
            }) { gatewaysLiveData.postValue(it) }
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
