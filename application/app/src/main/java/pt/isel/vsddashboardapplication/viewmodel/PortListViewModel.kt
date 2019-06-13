package pt.isel.vsddashboardapplication.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import pt.isel.vsddashboardapplication.repository.NSGatewayRepository
import pt.isel.vsddashboardapplication.repository.PortRepository
import pt.isel.vsddashboardapplication.repository.pojo.NSPort

class PortListViewModel : ViewModel() {

    private lateinit var portList : LiveData<List<NSPort>>
    private lateinit var repo: PortRepository
    private lateinit var nsg : String

    fun init(portRepository: PortRepository, nsgId: String){
        this.nsg = nsgId
        this.repo = portRepository
        viewModelScope.launch {
            portList = repo.getForNSGateway(nsgId)
        }
    }

    fun update(){

    }

}
