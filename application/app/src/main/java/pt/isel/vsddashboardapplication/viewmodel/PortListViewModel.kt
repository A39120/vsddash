package pt.isel.vsddashboardapplication.viewmodel

import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch
import pt.isel.vsddashboardapplication.repository.PortRepository
import pt.isel.vsddashboardapplication.repository.pojo.NSPort

class PortListViewModel : ViewModel() {


    val portList by lazy {  MediatorLiveData<List<NSPort>>() }
    private lateinit var repo: PortRepository
    private lateinit var nsg : String

    fun init(portRepository: PortRepository, nsgId: String){
        this.nsg = nsgId
        this.repo = portRepository
        viewModelScope.launch {
            this@PortListViewModel.portList.addSource(repo.getForNSGateway(nsg)) { portList.value = it }
            cyclicUpdate()

        }
    }

    fun update() = viewModelScope.launch { repo.updateAll(nsg) }

    private fun cyclicUpdate() = viewModelScope.launch {
        while(this.isActive) {
            update()
            delay(5000L)
        }
    }
}
