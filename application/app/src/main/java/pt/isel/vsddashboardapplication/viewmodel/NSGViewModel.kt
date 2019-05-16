package pt.isel.vsddashboardapplication.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import pt.isel.vsddashboardapplication.repository.NSGatewayRepository
import pt.isel.vsddashboardapplication.repository.pojo.NSGateway

class NSGViewModel : ViewModel() {

    private lateinit var repo: NSGatewayRepository

    lateinit var nsg: LiveData<NSGateway>

    fun init(repo: NSGatewayRepository, id: String){
        this.repo = repo
        this.nsg = repo.get(id)
    }
}