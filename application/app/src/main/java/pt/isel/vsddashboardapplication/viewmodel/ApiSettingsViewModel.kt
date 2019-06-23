package pt.isel.vsddashboardapplication.viewmodel

import androidx.lifecycle.ViewModel
import pt.isel.vsddashboardapplication.repository.ApiSettingsRepo

class ApiSettingsViewModel : ViewModel(){

    private lateinit var repository: ApiSettingsRepo

    var address: String? = null
    var apiPort: Int? = null
    var monitPort : Int? = null

    fun init(apiDetailsRepo: ApiSettingsRepo){
        repository = apiDetailsRepo

        //Initiate variables
        address = apiDetailsRepo.getAddress()
        apiPort = apiDetailsRepo.getVSDPort()
        monitPort = apiDetailsRepo.getMonitPort()
    }

    fun updateAddress(address: String?) : Unit = repository.updateAddress(address)
    fun updateApiPort(apiPort: Int?) : Unit = repository.updateVSDPort(apiPort)
    fun updateMonitPort(monitPort: Int?) : Unit = repository.updateMonitPort(monitPort)

}