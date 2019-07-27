package pt.isel.vsddashboardapplication.viewmodel

import android.util.Log
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import pt.isel.vsddashboardapplication.model.NSGateway
import pt.isel.vsddashboardapplication.repository.base.NSGatewayRepository
import pt.isel.vsddashboardapplication.utils.RefreshState
import pt.isel.vsddashboardapplication.viewmodel.base.BaseViewModel
import javax.inject.Inject

/**
 * NSG View Model
 */
class NSGViewModel @Inject constructor(private val repository: NSGatewayRepository) : BaseViewModel<NSGateway>() {
    companion object {
        private const val TAG = "VM/NSG"
    }

    override suspend fun setLiveData() {
        Log.d(TAG, "Setting liveData with NSG with ID: $id (repository = ${repository.javaClass}")
        repository.let {repo -> this.liveData.addSource(repo.get(id)) { liveData.value = it } }
    }

    override suspend fun updateLiveData() {
        Log.d(TAG, "Updating liveData with NSG with ID: $id (repository = ${repository.javaClass}")
        this.refreshStateLiveData.postValue(RefreshState.INPROGRESS)
        this.repository.update(id) {
            this.refreshStateLiveData.postValue(RefreshState.NONE)
        }
    }

    private lateinit var id: String

    /**
     * Initiates the view model, by extracting the data;
     */
    fun init(id: String){
        Log.d(TAG, "Setting NSG ID: $id (repository = ${repository.javaClass}")
        this.id = id
        viewModelScope.launch { setLiveData() }
    }

}






