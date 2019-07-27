package pt.isel.vsddashboardapplication.viewmodel

import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import pt.isel.vsddashboardapplication.model.VRS
import pt.isel.vsddashboardapplication.model.VSC
import pt.isel.vsddashboardapplication.repository.base.VrsRepository
import pt.isel.vsddashboardapplication.repository.base.VscRepository
import pt.isel.vsddashboardapplication.utils.RefreshState
import pt.isel.vsddashboardapplication.viewmodel.base.BaseListViewModel
import javax.inject.Inject

class VscListViewModel @Inject constructor(
    private val repository: VscRepository
) : BaseListViewModel<VSC>() {

    private lateinit var parent: String

    override suspend fun setLiveData() {
        liveData.addSource(repository.getAll(parent)){ liveData.value = it}
    }

    override suspend fun updateLiveData() {
        refreshStateLiveData.postValue(RefreshState.INPROGRESS)
        repository.update(parent) { refreshStateLiveData.postValue(RefreshState.NONE) }
    }

    /**
     * Sets up the View Model for the component using it
     * @param parentId: if not null, it represents the VSC, if null it will get all the global VRSs
     */
    fun init(parentId: String){
        this.parent = parentId
        viewModelScope.launch { setLiveData() }
    }

}
