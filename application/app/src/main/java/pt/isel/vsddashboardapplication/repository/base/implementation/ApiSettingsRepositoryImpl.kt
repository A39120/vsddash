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
class ApiSettingsRepositoryImpl @Inject constructor(private val sharedPrefs: SharedPreferences) :
    ApiSettingsRepository {
    companion object{
        private const val TAG = "REPO/APISETT"
    }

    private var address : String?
    private var vsdApi : Int?
    private var monit : Int?

    init {
        address = sharedPrefs.getAddress()
        vsdApi = sharedPrefs.getVsdPort()
        monit = sharedPrefs.getMonitPort()
    }


    override fun getAddress(): String  = address ?: ""
    override fun getVSDPort(): Int = vsdApi ?: 0
    override fun getMonitPort(): Int = monit ?: 0

    override fun updateAddress(address: String?) {
        Log.d(TAG, "Updating address with $address?:'null'")
        sharedPrefs.setAddress(address)
        this.address = address
    }

    override fun updateVSDPort(port: Int?) {
        Log.d(TAG, "Updating VSD Port with $port")
        sharedPrefs.setVsdPort(port)
        this.vsdApi = port
    }

    override fun updateMonitPort(port: Int?) {
        Log.d(TAG, "Updating Monit Port with $port")
        sharedPrefs.setMonitPort(monit)
        this.monit = port
    }

    fun updateElasticSearchPort(port: Int?) {
        //TODO: Do not forget this one
        Log.d(TAG, "Updating Monit Port with $port")
        sharedPrefs.setElasticSearchPort(port)
        //this.monit = port
    }
}