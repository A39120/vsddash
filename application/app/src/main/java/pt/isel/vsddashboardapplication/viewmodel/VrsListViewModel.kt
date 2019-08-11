package pt.isel.vsddashboardapplication.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import pt.isel.vsddashboardapplication.model.VRS
import pt.isel.vsddashboardapplication.repository.base.VrsRepository
import pt.isel.vsddashboardapplication.utils.RefreshState
import pt.isel.vsddashboardapplication.viewmodel.base.BaseListViewModel
import java.util.function.Supplier
import javax.inject.Inject

class VrsListViewModel @Inject constructor(
    private val repository: VrsRepository
) : BaseListViewModel<VRS>() {

    private var parent : String? = null

    override suspend fun setLiveData() {
        lateinit var getter : Supplier<LiveData<List<VRS>?>>
        lateinit var updater : Runnable

         if(parent != null) {
            getter = Supplier { repository.getAll(parent!!) }
            updater = Runnable {
                viewModelScope.launch { repository.updateAll(parent!!) }
            }
        } else {
            getter = Supplier { repository.getGlobal() }
            updater = Runnable { viewModelScope.launch { repository.updateGlobal() } }
        }

        liveData.addSource(getter.get()) { liveData.value = it }
        if(liveData.value == null)
            updater.run()
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