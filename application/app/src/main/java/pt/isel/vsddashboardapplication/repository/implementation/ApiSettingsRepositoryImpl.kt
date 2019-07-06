package pt.isel.vsddashboardapplication.repository.implementation

import android.content.SharedPreferences
import android.util.Log
import pt.isel.vsddashboardapplication.repository.ApiSettingsRepository
import pt.isel.vsddashboardapplication.utils.SharedPreferenceKeys
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
        address = sharedPrefs.getString(SharedPreferenceKeys.CURRENTADDRESS, SharedPreferenceKeys.DEFAULTADDRESS)
        vsdApi = sharedPrefs.getInt(SharedPreferenceKeys.CURRENTPORT, SharedPreferenceKeys.PORTDEFAULT)
        monit = sharedPrefs.getInt(SharedPreferenceKeys.MONIT_PORT, SharedPreferenceKeys.MONIT_PORT_DEFAULT)
    }


    override fun getAddress(): String  = address ?: ""
    override fun getVSDPort(): Int = vsdApi ?: 0
    override fun getMonitPort(): Int = monit ?: 0

    override fun updateAddress(address: String?) {
        Log.d(TAG, "Updating address with $address")
        sharedPrefs.edit().let {
            it.putString(SharedPreferenceKeys.CURRENTADDRESS, address)
            it.apply()
        }
        this.address = address
    }

    override fun updateVSDPort(port: Int?) {
        Log.d(TAG, "Updating VSD Port with $port")
        sharedPrefs.edit().let {
            it.putInt(SharedPreferenceKeys.CURRENTPORT, port?:SharedPreferenceKeys.PORTDEFAULT )
            it.apply()
        }
        this.vsdApi = port
    }

    override fun updateMonitPort(port: Int?) {
        Log.d(TAG, "Updating Monit Port with $port")
        sharedPrefs.edit().let {
            it.putInt(SharedPreferenceKeys.MONIT_PORT, port?:SharedPreferenceKeys.MONIT_PORT_DEFAULT )
            it.apply()
        }
        this.monit = port
    }

}