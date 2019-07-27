package pt.isel.vsddashboardapplication.viewmodel

import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import pt.isel.vsddashboardapplication.model.VRS
import pt.isel.vsddashboardapplication.repository.base.VrsRepository
import pt.isel.vsddashboardapplication.utils.RefreshState
import pt.isel.vsddashboardapplication.viewmodel.base.BaseListViewModel
import javax.inject.Inject

class VrsListViewModel @Inject constructor(
    private val repository: VrsRepository
) : BaseListViewModel<VRS>() {

    private var parent : String? = null

    override suspend fun setLiveData() {
        parent?.let { pid ->
            liveData.addSource(repository.getAll(pid)){ liveData.value = it}
        }?: liveData.addSource(repository.getGlobal()){ liveData.value = it}
    }

    override suspend fun updateLiveData() {
        refreshStateLiveData.value = RefreshState.INPROGRESS
        parent?.let { parentId ->
            repository.updateAll(parentId) { refreshStateLiveData.postValue(RefreshState.NONE) }
        }?: repository.updateGlobal { refreshStateLiveData.postValue(RefreshState.NONE) }
    }

    /**
     * Sets up the View Model for the component using it
     * @param parentId: if not null, it represents the VSC, if null it will get all the global VRSs
     */
    fun init(parentId: String? = null){
        this.parent = parentId
        viewModelScope.launch { setLiveData() }
    }

}