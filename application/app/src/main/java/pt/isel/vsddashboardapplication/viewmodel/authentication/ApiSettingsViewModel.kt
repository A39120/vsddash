package pt.isel.vsddashboardapplication.viewmodel.authentication

import androidx.lifecycle.ViewModel
import pt.isel.vsddashboardapplication.repository.base.ApiSettingsRepository
import javax.inject.Inject

class ApiSettingsViewModel @Inject constructor(
    private val repository: ApiSettingsRepository
): ViewModel() {
    companion object{ private const val TAG = "VM/APISETT" }

    var address: String? = repository.getAddress()
    var apiPort: Int? = repository.getVSDPort()
    var esPort: Int? = repository.getElasticSearchPort()
    var monitPort : Int? = repository.getMonitPort()

    fun updateAddress(address: String?) : Unit = repository.updateAddress(address)
    fun updateApiPort(apiPort: Int?) : Unit = repository.updateVSDPort(apiPort)
    fun updateMonitPort(monitPort: Int?) : Unit = repository.updateMonitPort(monitPort)
    fun updateElasticSearchPort(port: Int?) : Unit = repository.updateElasticSearchPort(port)

}