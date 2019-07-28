package pt.isel.vsddashboardapplication.utils

import android.content.Context
import android.content.SharedPreferences
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKeys


/**
 * Contains all the useful stuff used in shared preferences
 */
object SharedPreferencesUtils {
    const val PORT_KEY = "currentport"
    const val PORT_DEFAULT = 8443

    const val MONIT_PORT_KEY = "currentmonitport"
    const val MONIT_PORT_DEFAULT = 1443

    const val ES_PORT_KEY = "currentesport"
    const val ES_PORT_DEFAULT = 9600

    const val ADDRESS_KEY = "currentaddress"
    const val ADDRESS_DEFAULT = "127.0.0.1"


    const val USERNAME_KEY = "username"
    const val PASSWORD_KEY = "password"
    const val ORGANIZATION_KEY = "organization"

    const val API_KEY = "vsdapikey"
    const val API_EXPIRY_KEY = "vsdapikey"
    const val API_EXPIRY_DEFAULT : Long = 0

    const val VSD_AUTOMATIC_UPDATE_KEY = "vsdautoupdate"

    /**
     * Creates or gets a new encrypted shared preferences instance
     * @param context: the context used to access the shared preferences
     * @return the shared preferences of the application
     */
    fun createOrGet(context: Context) : SharedPreferences {
        val filename = "enc_sp"
        val masterKeyAlias = MasterKeys.getOrCreate(MasterKeys.AES256_GCM_SPEC)
        val sharedPreferences =
            EncryptedSharedPreferences.create(filename,
                masterKeyAlias,
                context,
                EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
                EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM)

        return sharedPreferences
    }


}

fun SharedPreferences.getUsername() = this.getString(SharedPreferencesUtils.USERNAME_KEY, null)
fun SharedPreferences.getPassword() = this.getString(SharedPreferencesUtils.PASSWORD_KEY, null)
fun SharedPreferences.getOrganization() = this.getString(SharedPreferencesUtils.ORGANIZATION_KEY, null)

fun SharedPreferences.getAddress() = this.getString(
    SharedPreferencesUtils.ADDRESS_KEY,
    SharedPreferencesUtils.ADDRESS_DEFAULT
)
fun SharedPreferences.getVsdPort() = this.getInt(
    SharedPreferencesUtils.PORT_KEY,
    SharedPreferencesUtils.PORT_DEFAULT
)
fun SharedPreferences.getMonitPort() = this.getInt(
    SharedPreferencesUtils.MONIT_PORT_KEY,
    SharedPreferencesUtils.MONIT_PORT_DEFAULT
)
fun SharedPreferences.getElasticSearchPort() = this.getInt(
    SharedPreferencesUtils.ES_PORT_KEY,
    SharedPreferencesUtils.ES_PORT_DEFAULT
)

fun SharedPreferences.getApiKey() = this.getString(SharedPreferencesUtils.API_KEY, null)
fun SharedPreferences.getApiExpiration() = this.getLong(
    SharedPreferencesUtils.API_EXPIRY_KEY,
    SharedPreferencesUtils.API_EXPIRY_DEFAULT
)


fun SharedPreferences.setUsername(username: String?) {
    this.edit().apply{
        putString(SharedPreferencesUtils.USERNAME_KEY, username)
        apply()
    }
}

fun SharedPreferences.setPassword(password: String?) {
    this.edit().apply{
        putString(SharedPreferencesUtils.PASSWORD_KEY, password)
        apply()
    }
}

fun SharedPreferences.setOrganization(organization: String?) {
    this.edit().apply{
        putString(SharedPreferencesUtils.ORGANIZATION_KEY, organization)
        apply()
    }
}

fun SharedPreferences.setAddress(address: String?) {
    this.edit().apply {
        putString(SharedPreferencesUtils.ADDRESS_KEY, address?:SharedPreferencesUtils.ADDRESS_DEFAULT)
        apply()
    }
}

fun SharedPreferences.setVsdPort(port: Int?) {
    this.edit().apply {
        putInt(SharedPreferencesUtils.PORT_KEY, port?: SharedPreferencesUtils.PORT_DEFAULT)
        apply()
    }
}

fun SharedPreferences.setMonitPort(monitPort: Int?) {
    this.edit().apply {
        putInt(SharedPreferencesUtils.MONIT_PORT_KEY, monitPort?: SharedPreferencesUtils.MONIT_PORT_DEFAULT)
        apply()
    }
}

fun SharedPreferences.setElasticSearchPort(esPort: Int?) {
    this.edit().apply {
        putInt(SharedPreferencesUtils.ES_PORT_KEY, esPort?: SharedPreferencesUtils.ES_PORT_DEFAULT)
        apply()
    }
}


fun SharedPreferences.setApiKey(apiKey: String?) {
    this.edit().apply{
        putString(SharedPreferencesUtils.API_KEY, apiKey)
        apply()
    }
}

fun SharedPreferences.setApiExpiration(expiration: Long?) {
    this.edit().apply {
        putLong(SharedPreferencesUtils.API_EXPIRY_KEY, expiration?: SharedPreferencesUtils.API_EXPIRY_DEFAULT)
        apply()
    }
}

fun SharedPreferences.getVsdAutomaticUpdate() = this.getBoolean(SharedPreferencesUtils.VSD_AUTOMATIC_UPDATE_KEY, false)
fun SharedPreferences.setVsdAutomaticUpdate(value: Boolean) {
    edit().apply {
        putBoolean(SharedPreferencesUtils.VSD_AUTOMATIC_UPDATE_KEY, value)
        apply()
    }
}

fun Context.sharedPreferences() = SharedPreferencesUtils.createOrGet(this)
