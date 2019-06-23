package pt.isel.vsddashboardapplication.repository.implementation

import android.content.SharedPreferences
import pt.isel.vsddashboardapplication.repository.ApiSettingsRepo
import pt.isel.vsddashboardapplication.utils.SharedPreferenceKeys

/**
 * API Settings repository
 * Only interacts with Shared Preferences
 */
class ApiSettingsRepoImpl(private val sharedPrefs: SharedPreferences) :
    ApiSettingsRepo {

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
        sharedPrefs.edit().let {
            it.putString(SharedPreferenceKeys.CURRENTADDRESS, address)
            it.apply()
        }
        this.address = address
    }

    override fun updateVSDPort(port: Int?) {
        sharedPrefs.edit().let {
            it.putInt(SharedPreferenceKeys.CURRENTPORT, port?:SharedPreferenceKeys.PORTDEFAULT )
            it.apply()
        }
        this.vsdApi = port
    }

    override fun updateMonitPort(port: Int?) {
        sharedPrefs.edit().let {
            it.putInt(SharedPreferenceKeys.MONIT_PORT, port?:SharedPreferenceKeys.MONIT_PORT_DEFAULT )
            it.apply()
        }
        this.monit = port
    }

}