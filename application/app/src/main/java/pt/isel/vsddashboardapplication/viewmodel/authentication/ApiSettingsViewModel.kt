package pt.isel.vsddashboardapplication.viewmodel.authentication

import android.util.Log
import androidx.lifecycle.ViewModel
import pt.isel.vsddashboardapplication.repository.base.ApiSettingsRepository
import javax.inject.Inject

class ApiSettingsViewModel @Inject constructor(private val repository: ApiSettingsRepository): ViewModel(){
    companion object{
        private const val TAG = "VM/APISETT"
    }

    var address: String? = null
    var apiPort: Int? = null
    var monitPort : Int? = null

    fun init(){
        Log.d(TAG, "Setting up API settings view model")

        //Initiate variables
        address = repository.getAddress()
        apiPort = repository.getVSDPort()
        monitPort = repository.getMonitPort()
    }

    fun updateAddress(address: String?) : Unit = repository.updateAddress(address)
    fun updateApiPort(apiPort: Int?) : Unit = repository.updateVSDPort(apiPort)
    fun updateMonitPort(monitPort: Int?) : Unit = repository.updateMonitPort(monitPort)

}