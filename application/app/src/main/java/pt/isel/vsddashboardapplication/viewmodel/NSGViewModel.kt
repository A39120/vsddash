package pt.isel.vsddashboardapplication.viewmodel

import androidx.lifecycle.*
import kotlinx.coroutines.*
import pt.isel.vsddashboardapplication.repository.NSGatewayRepository
import pt.isel.vsddashboardapplication.repository.pojo.NSGateway

/**
 * NSG View Model
 */
class NSGViewModel : ViewModel() {

    /**
     * The repository to obtain the current NSG information
     */
    private lateinit var repo: NSGatewayRepository

    val nsg by lazy {  MediatorLiveData<NSGateway>() }
    private lateinit var id: String

    /**
     * Initiates the view model, by extracting the data;
     */
    fun init(repo: NSGatewayRepository, id: String){
        this.repo = repo
        this.id = id
        viewModelScope.launch {
            this@NSGViewModel.nsg.addSource(repo.get(id)) { nsg.value = it }
            cyclicUpdate()
        }
    }

    /**
     * Updates the information;
     */
    private fun cyclicUpdate() = viewModelScope.launch {
        while (this.isActive) {
            repo.update(id)
            delay(5000L)
        }
    }


}






