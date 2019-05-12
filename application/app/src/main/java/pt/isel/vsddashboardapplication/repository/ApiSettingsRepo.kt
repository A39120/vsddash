package pt.isel.vsddashboardapplication.repository

import androidx.lifecycle.LiveData

interface ApiSettingsRepo {

    fun getAddress() : LiveData<String?>
    fun getVSDPort() : LiveData<Int?>
    fun getMonitPort(): LiveData<Int?>

    fun updateAddress(address: String?)
    fun updateVSDPort(port: Int?)
    fun updateMonitPort(port: Int?)

}