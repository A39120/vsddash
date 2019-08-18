package pt.isel.vsddashboardapplication.repository.base.implementation

import android.content.SharedPreferences
import android.util.Log
import pt.isel.vsddashboardapplication.repository.base.ApiSettingsRepository
import pt.isel.vsddashboardapplication.utils.*
import javax.inject.Inject

/**
 * API Settings repository
 * Only interacts with Shared Preferences
 */
class ApiSettingsRepositoryImpl @Inject constructor(
    private val sharedPrefs: SharedPreferences
) : ApiSettingsRepository {
    companion object{ private const val TAG = "REPO/APISETT" }

    override fun getAddress(): String  = sharedPrefs.getAddress() ?: ""
    override fun getVSDPort(): Int = sharedPrefs.getVsdPort()
    override fun getMonitPort(): Int = sharedPrefs.getMonitPort()
    override fun getElasticSearchPort(): Int = sharedPrefs.getElasticSearchPort()

    override fun updateAddress(address: String?) {
        Log.d(TAG, "Updating address with $address?:'null'")
        sharedPrefs.setAddress(address)
    }

    override fun updateVSDPort(port: Int?) {
        Log.d(TAG, "Updating VSD Port with $port")
        sharedPrefs.setVsdPort(port)
    }

    override fun updateMonitPort(port: Int?) {
        Log.d(TAG, "Updating Monit Port with $port")
        sharedPrefs.setMonitPort(port)
    }

    override fun updateElasticSearchPort(port: Int?) {
        Log.d(TAG, "Updating ElasticSearch Port with $port")
        sharedPrefs.setElasticSearchPort(port)
    }


}