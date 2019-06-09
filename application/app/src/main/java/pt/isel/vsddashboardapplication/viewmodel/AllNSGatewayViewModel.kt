package pt.isel.vsddashboardapplication.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import pt.isel.vsddashboardapplication.repository.NSGatewayRepository
import pt.isel.vsddashboardapplication.repository.pojo.NSGateway

/**
 * View model of multiple NSGs
 */
class AllNSGatewayViewModel(
    private val repo: NSGatewayRepository,
    private val enterprise: String
) : ViewModel(){

    val gateways : LiveData<List<NSGateway>> = repo.getAll(enterprise)


}