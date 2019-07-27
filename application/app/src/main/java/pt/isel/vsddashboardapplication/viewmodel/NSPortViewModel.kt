package pt.isel.vsddashboardapplication.viewmodel

import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import pt.isel.vsddashboardapplication.model.NSGInfo
import pt.isel.vsddashboardapplication.model.NSPort
import pt.isel.vsddashboardapplication.repository.base.NSGinfoRepository
import pt.isel.vsddashboardapplication.repository.base.PortRepository
import pt.isel.vsddashboardapplication.utils.RefreshState
import pt.isel.vsddashboardapplication.viewmodel.base.BaseViewModel
import javax.inject.Inject

class NSPortViewModel @Inject constructor(
    private val repository: PortRepository,
    private val nsgRepository: NSGinfoRepository
) : BaseViewModel<NSPort>() {

    private var portId: String? = null
    private var nsgId: String? = null

    val nsgLiveData = MediatorLiveData<NSGInfo>()

    override suspend fun setLiveData() {
        portId?.let { portId -> liveData.addSource(repository.get(portId)) { port -> liveData.value = port} }
        nsgId?.let { nsgId -> liveData.addSource(nsgRepository.get(nsgId)) { nsg -> nsgLiveData.value = nsg} }
    }

    override suspend fun updateLiveData() {
        portId?.let {
            this.refreshStateLiveData.postValue(RefreshState.INPROGRESS)
            repository.update(it) { this.refreshStateLiveData.postValue(RefreshState.NONE) }
        }
    }

    fun init(portId: String? = null, nsgId: String? = null){
        this.portId = portId
        this.nsgId = nsgId
        viewModelScope.launch { setLiveData() }
    }

}