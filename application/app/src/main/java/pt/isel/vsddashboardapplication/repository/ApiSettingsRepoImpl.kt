package pt.isel.vsddashboardapplication.repository

import android.content.SharedPreferences
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import pt.isel.vsddashboardapplication.utils.SharedPreferenceKeys

class ApiSettingsRepoImpl(private val sharedPrefs: SharedPreferences) : ApiSettingsRepo{

    private val address = MutableLiveData<String?>()
    private val vsdApi = MutableLiveData<Int?>()
    private val monit = MutableLiveData<Int?>()

    init {
        val add = sharedPrefs.getString(SharedPreferenceKeys.CURRENTADDRESS, SharedPreferenceKeys.DEFAULTADDRESS)
        val vsdPort = sharedPrefs.getInt(SharedPreferenceKeys.CURRENTPORT, SharedPreferenceKeys.PORTDEFAULT)

        address.postValue(add)
        vsdApi.postValue(vsdPort)
    }


    override fun getAddress(): LiveData<String?>  = address
    override fun getVSDPort(): LiveData<Int?> = vsdApi
    override fun getMonitPort(): LiveData<Int?> = monit

    override fun updateAddress(address: String?) {
        sharedPrefs.edit().let {
            it.putString(SharedPreferenceKeys.CURRENTADDRESS, address)
            it.apply()
        }
        this.address.postValue(address)
    }

    override fun updateVSDPort(port: Int?) {
        sharedPrefs.edit().let {
            it.putInt(SharedPreferenceKeys.CURRENTADDRESS, port?:SharedPreferenceKeys.PORTDEFAULT )
            it.apply()
        }
        this.vsdApi.postValue(port)
    }

    override fun updateMonitPort(port: Int?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}