package pt.isel.vsddashboardapplication.viewmodel

import android.util.Log
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import pt.isel.vsddashboardapplication.repository.NSGatewayRepository
import pt.isel.vsddashboardapplication.model.NSGateway
import javax.inject.Inject

/**
 * View model of multiple NSGs
 */
class AllNSGatewayViewModel @Inject constructor(private val repository: NSGatewayRepository): BaseListViewModel<NSGateway>(){
    companion object{
        private const val TAG = "VM/NSGLIST"
    }

    private lateinit var enterprise: String

    fun init(enterprise: String = "") {
        Log.d(TAG, "Setting enterprise id = $enterprise")
        this.enterprise = enterprise
        viewModelScope.launch { setLiveData() }
    }

    override suspend fun setLiveData() {
        Log.d(TAG, "Updating all NSGateways for enterprise $enterprise (repository = ${repository.hashCode()?:0})")
        repository?.let {
            this.liveData.addSource(it.getAll(enterprise)) { nsgs -> liveData.value = nsgs }
        }
    }

    override suspend fun updateLiveData() {
        Log.d(TAG, "Updating all NSGateways for enterprise $enterprise (repository = ${repository.hashCode()?:0})")
        repository?.updateAll(enterprise)
    }

}