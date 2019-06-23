package pt.isel.vsddashboardapplication.viewmodel

import androidx.lifecycle.ViewModel
import pt.isel.vsddashboardapplication.repository.ApiSettingsRepository

class ApiSettingsViewModel : ViewModel(){

    private lateinit var repository: ApiSettingsRepository

    var address: String? = null
    var apiPort: Int? = null
    var monitPort : Int? = null

    fun init(apiDetailsRepository: ApiSettingsRepository){
        repository = apiDetailsRepository

        //Initiate variables
        address = apiDetailsRepository.getAddress()
        apiPort = apiDetailsRepository.getVSDPort()
        monitPort = apiDetailsRepository.getMonitPort()
    }

    fun updateAddress(address: String?) : Unit = repository.updateAddress(address)
    fun updateApiPort(apiPort: Int?) : Unit = repository.updateVSDPort(apiPort)
    fun updateMonitPort(monitPort: Int?) : Unit = repository.updateMonitPort(monitPort)

}