package pt.isel.vsddashboardapplication.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import pt.isel.vsddashboardapplication.repository.ApiSettingsRepo

class ApiSettingsViewModel : ViewModel(){

    lateinit var address: LiveData<String?>
    lateinit var apiPort: LiveData<Int?>
    lateinit var monitPort : LiveData<Int?>

    fun init(apiDetailsRepo: ApiSettingsRepo){
        address = apiDetailsRepo.getAddress()
        apiPort = apiDetailsRepo.getVSDPort()
        monitPort = apiDetailsRepo.getMonitPort()
    }

}