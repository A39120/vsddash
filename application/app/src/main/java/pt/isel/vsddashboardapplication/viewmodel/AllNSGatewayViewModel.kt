package pt.isel.vsddashboardapplication.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import pt.isel.vsddashboardapplication.repository.NSGatewayRepository
import pt.isel.vsddashboardapplication.repository.pojo.NSGateway

/**
 * View model of multiple NSGs
 */
class AllNSGatewayViewModel : ViewModel(){

    private lateinit var repository: NSGatewayRepository
    lateinit var gateways : LiveData<List<NSGateway>>

    fun init(repo: NSGatewayRepository){
        this.repository = repo

        //Get gateways
        this.gateways = this.repository.getAll()
    }

}