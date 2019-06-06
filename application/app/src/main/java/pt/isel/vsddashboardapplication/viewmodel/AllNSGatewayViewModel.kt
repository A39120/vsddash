package pt.isel.vsddashboardapplication.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import pt.isel.vsddashboardapplication.repository.NSGatewayRepository
import pt.isel.vsddashboardapplication.repository.pojo.NSGateway

/**
 * View model of multiple NSGs
 */
class AllNSGatewayViewModel : ViewModel(){

    private lateinit var repo: NSGatewayRepository
    lateinit var gateways : LiveData<List<NSGateway>>

    fun init(repo: NSGatewayRepository, enterprise: String){
        this.repo = repo

        //Get gateways
        this.gateways = repo.getAll(enterprise)
    }



}