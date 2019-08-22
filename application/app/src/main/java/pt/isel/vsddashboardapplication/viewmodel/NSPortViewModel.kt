package pt.isel.vsddashboardapplication.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import pt.isel.vsddashboardapplication.model.Alarm
import pt.isel.vsddashboardapplication.model.NSGInfo
import pt.isel.vsddashboardapplication.model.NSPort
import pt.isel.vsddashboardapplication.repository.base.NSGinfoRepository
import pt.isel.vsddashboardapplication.repository.base.NSPortRepository
import pt.isel.vsddashboardapplication.utils.RefreshState
import pt.isel.vsddashboardapplication.viewmodel.base.BaseViewModel
import pt.isel.vsddashboardapplication.viewmodel.parent.AlarmParentViewModel
import javax.inject.Inject

class NSPortViewModel @Inject constructor(
    private val repositoryNS: NSPortRepository,
    private val nsgRepository: NSGinfoRepository
) : BaseViewModel<NSPort>(), AlarmParentViewModel {

    private var portId: String? = null
    private var nsgId: String? = null

    val nsgLiveData = MediatorLiveData<NSGInfo>()

    private val alarmsRefreshStateLiveData = MediatorLiveData<RefreshState>()
    private val alarmsLiveData = MediatorLiveData<List<Alarm>?>()

    override fun getAlarmsLiveData(): LiveData<List<Alarm>?> = this.alarmsLiveData
    override fun getRefreshState(): LiveData<RefreshState> = this.alarmsRefreshStateLiveData
    override fun updateAlarmsLiveData() {
        viewModelScope.launch {
            alarmsRefreshStateLiveData.postValue(RefreshState.INPROGRESS)
            repositoryNS.updateAlarms(portId?:liveData.value!!.iD) { alarmsRefreshStateLiveData.postValue(RefreshState.NONE) }
        }
    }

    override suspend fun setLiveData() {
        nsgId?.let { nsgId -> liveData.addSource(nsgRepository.get(nsgId)) { nsg -> nsgLiveData.value = nsg} }
        portId?.let { portId ->
            liveData.addSource(repositoryNS.get(portId)) { port -> liveData.value = port}
            if(liveData.value == null)
                repositoryNS.get(portId)

            alarmsLiveData.addSource(Transformations.switchMap(liveData) {
                val ld = repositoryNS.getAlarms(it.iD)
                if(ld.value == null)
                    viewModelScope.launch { repositoryNS.updateAlarms(it.iD) }
                ld
            }) {
                alarmsLiveData.value = it
            }
        }
    }

    override suspend fun updateLiveData() {
        portId?.let {
            this.refreshStateLiveData.postValue(RefreshState.INPROGRESS)
            repositoryNS.update(it) { this.refreshStateLiveData.postValue(RefreshState.NONE) }
        }
    }

    fun init(portId: String? = null, nsgId: String? = null){
        this.portId = portId
        this.nsgId = nsgId
        viewModelScope.launch { setLiveData() }
    }

}