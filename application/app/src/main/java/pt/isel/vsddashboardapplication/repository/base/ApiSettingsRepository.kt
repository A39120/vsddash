package pt.isel.vsddashboardapplication.repository.base

interface ApiSettingsRepository  {

    fun getAddress() : String
    fun getVSDPort() : Int
    fun getMonitPort(): Int

    fun updateAddress(address: String?)
    fun updateVSDPort(port: Int?)
    fun updateMonitPort(port: Int?)

    fun updateElasticSearchPort(port: Int?)
    fun getElasticSearchPort(): Int
}