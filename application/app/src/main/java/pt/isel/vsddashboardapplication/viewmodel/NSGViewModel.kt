package pt.isel.vsddashboardapplication.viewmodel

import android.util.Log
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import pt.isel.vsddashboardapplication.model.NSGateway
import pt.isel.vsddashboardapplication.repository.NSGatewayRepository
import javax.inject.Inject

/**
 * NSG View Model
 */
class NSGViewModel : BaseViewModel<NSGateway>() {
    companion object {
        private const val TAG = "VM/NSG"
    }

    override suspend fun setLiveData() {
        Log.d(TAG, "Setting livedata with NSG with ID: $id (repository = ${repository?.hashCode() ?: 0}")
        repository?.let {repo -> this.liveData.addSource(repo.get(id)) { liveData.value = it } }
    }

    override suspend fun updateLiveData() {
        Log.d(TAG, "Updating livedata with NSG with ID: $id (repository = ${repository?.hashCode() ?: 0}")
        this.repository?.update(id)
    }

    /**
     * The repository to obtain the current NSG information
     */
    @Inject var repository: NSGatewayRepository? = null
    private lateinit var id: String

    /**
     * Initiates the view model, by extracting the data;
     */
    fun init(id: String){
        Log.d(TAG, "Setting NSG ID: $id (repository = ${repository?.hashCode() ?: 0}")
        this.id = id
        viewModelScope.launch { setLiveData() }
    }

}






