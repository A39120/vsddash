package pt.isel.vsddashboardapplication.viewmodel

import androidx.lifecycle.*
import kotlinx.coroutines.*
import pt.isel.vsddashboardapplication.repository.NSGatewayRepository
import pt.isel.vsddashboardapplication.repository.pojo.NSGateway

class NSGViewModel : ViewModel() {

    /**
     * The repository to obtain the current NSG information
     */
    private lateinit var repo: NSGatewayRepository

    val nsg by lazy {  MediatorLiveData<NSGateway>() }
    private lateinit var id: String

    fun init(repo: NSGatewayRepository, id: String){
        this.repo = repo
        this.id = id
        viewModelScope.launch {
            this@NSGViewModel.nsg.addSource(repo.get(id)) { nsg.value = it }
            cyclicUpdate()
        }
    }

    private fun cyclicUpdate() = viewModelScope.launch {
        while (this.isActive) {
            repo.update(id)
            delay(30000L)
        }
    }


}






