package pt.isel.vsddashboardapplication.viewmodel

import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import pt.isel.vsddashboardapplication.repository.NSGatewayRepository
import pt.isel.vsddashboardapplication.model.NSGateway

/**
 * View model of multiple NSGs
 */
class AllNSGatewayViewModel : ViewModel(){

    private lateinit var repo: NSGatewayRepository
    private lateinit var enterprise: String
    val gateways = MediatorLiveData<List<NSGateway>>()

    fun init(repo: NSGatewayRepository, enterprise: String) {
        this.repo = repo
        this.enterprise = enterprise
        viewModelScope.launch {
            gateways.addSource(repo.getAll(enterprise)){ gateways.value = it }
        }
    }

}