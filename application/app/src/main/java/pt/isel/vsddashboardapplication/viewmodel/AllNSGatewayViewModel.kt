package pt.isel.vsddashboardapplication.viewmodel

import android.util.Log
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import pt.isel.vsddashboardapplication.repository.base.NSGatewayRepository
import pt.isel.vsddashboardapplication.model.NSGateway
import pt.isel.vsddashboardapplication.utils.RefreshState
import pt.isel.vsddashboardapplication.viewmodel.base.BaseListViewModel
import javax.inject.Inject

/**
 * View model of multiple NSGs
 */
class AllNSGatewayViewModel @Inject constructor(private val repository: NSGatewayRepository): BaseListViewModel<NSGateway>(){
    companion object{ private const val TAG = "VM/NSG_LIST" }

    private lateinit var enterprise: String

    fun init(enterprise: String = "") {
        Log.d(TAG, "Setting enterprise id = $enterprise")
        this.enterprise = enterprise
        viewModelScope.launch { setLiveData() }
    }

    override suspend fun setLiveData() {
        Log.d(TAG, "Updating all NSGateways for enterprise $enterprise (repository = ${repository.javaClass})")
        repository.let {
            val gateways = it.getAll(enterprise)
            this.liveData.addSource(gateways) { nsgs -> liveData.value = nsgs }
            if(gateways.value.isNullOrEmpty()) {
                Log.d(TAG, "NSG list is empty")
                repository.updateAll(enterprise)
            }
        }
    }

    override suspend fun updateLiveData() {
        Log.d(TAG, "Updating all NSGateways for enterprise $enterprise (repository = ${repository.javaClass})")
        this.refreshStateLiveData.postValue(RefreshState.INPROGRESS)
        repository.updateAll(enterprise){ this.refreshStateLiveData.postValue(RefreshState.NONE)}
    }

}