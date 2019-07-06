package pt.isel.vsddashboardapplication.viewmodel.authentication

import android.util.Log
import androidx.lifecycle.ViewModel
import pt.isel.vsddashboardapplication.repository.ApiSettingsRepository
import javax.inject.Inject

class ApiSettingsViewModel : ViewModel(){
    companion object{
        private const val TAG = "VM/APISETT"
    }

    @Inject lateinit var repository: ApiSettingsRepository

    var address: String? = null
    var apiPort: Int? = null
    var monitPort : Int? = null

    fun init(apiDetailsRepository: ApiSettingsRepository){
        Log.d(TAG, "Setting up API settings view model")
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