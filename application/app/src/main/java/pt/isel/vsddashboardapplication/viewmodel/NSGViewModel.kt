package pt.isel.vsddashboardapplication.viewmodel

import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import pt.isel.vsddashboardapplication.model.NSGateway
import pt.isel.vsddashboardapplication.repository.NSGatewayRepository

/**
 * NSG View Model
 */
class NSGViewModel : BaseViewModel<NSGateway>() {

    override suspend fun setLiveData() {
        this.liveData.addSource(repo.get(id)) { liveData.value = it }
    }

    override suspend fun updateLiveData() { this.repo.update(id) }

    /**
     * The repository to obtain the current NSG information
     */
    private lateinit var repo: NSGatewayRepository
    private lateinit var id: String

    /**
     * Initiates the view model, by extracting the data;
     */
    fun init(repo: NSGatewayRepository, id: String){
        this.repo = repo
        this.id = id
        viewModelScope.launch { setLiveData() }
    }

}






