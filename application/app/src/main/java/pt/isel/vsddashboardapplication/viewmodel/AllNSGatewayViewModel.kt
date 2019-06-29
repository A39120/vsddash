package pt.isel.vsddashboardapplication.viewmodel

import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import pt.isel.vsddashboardapplication.repository.NSGatewayRepository
import pt.isel.vsddashboardapplication.model.NSGateway

/**
 * View model of multiple NSGs
 */
class AllNSGatewayViewModel : BaseListViewModel<NSGateway>(){

    private lateinit var repo: NSGatewayRepository
    private lateinit var enterprise: String

    fun init(repo: NSGatewayRepository, enterprise: String) {
        this.repo = repo
        this.enterprise = enterprise
        viewModelScope.launch { setLiveData() }
    }

    override suspend fun setLiveData() {
        this.liveData.addSource(repo.getAll(enterprise)) { liveData.value = it}
    }

    override suspend fun updateLiveData() {
        repo.updateAll(enterprise)
    }

}