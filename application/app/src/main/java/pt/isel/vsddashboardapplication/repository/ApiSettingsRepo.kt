package pt.isel.vsddashboardapplication.repository

import androidx.lifecycle.LifecycleObserver

interface ApiSettingsRepo : LifecycleObserver {

    fun getAddress() : String
    fun getVSDPort() : Int
    fun getMonitPort(): Int

    fun updateAddress(address: String?)
    fun updateVSDPort(port: Int?)
    fun updateMonitPort(port: Int?)

}