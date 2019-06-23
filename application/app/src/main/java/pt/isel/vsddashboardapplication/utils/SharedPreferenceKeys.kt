package pt.isel.vsddashboardapplication.utils

import android.content.Context
import android.content.SharedPreferences
import android.util.Base64
import pt.isel.vsddashboardapplication.utils.SharedPreferenceKeys.APIKEY_EXPIRATION_KEY
import pt.isel.vsddashboardapplication.utils.SharedPreferenceKeys.APIKEY_KEY
import pt.isel.vsddashboardapplication.utils.SharedPreferenceKeys.BASE_SP
import pt.isel.vsddashboardapplication.utils.SharedPreferenceKeys.CURRENTADDRESS
import pt.isel.vsddashboardapplication.utils.SharedPreferenceKeys.CURRENTPORT
import pt.isel.vsddashboardapplication.utils.SharedPreferenceKeys.ORGANIZATION_KEY
import pt.isel.vsddashboardapplication.utils.SharedPreferenceKeys.PASSWORD_KEY
import pt.isel.vsddashboardapplication.utils.SharedPreferenceKeys.PORTDEFAULT
import pt.isel.vsddashboardapplication.utils.SharedPreferenceKeys.USERNAME_KEY


object SharedPreferenceKeys {
    const val MONIT_PORT: String = "vsdmonitport"
    const val MONIT_PORT_DEFAULT: Int = 8433
    const val BASE_SP = "vsdsharedpreferences"

    const val CURRENTPORT = "currentport"
    const val PORTDEFAULT = 1433

    const val CURRENTADDRESS = "currentaddress"
    const val DEFAULTADDRESS = "127.0.0.1"


    const val USERNAME_KEY = "username"
    const val PASSWORD_KEY = "password"
    const val ORGANIZATION_KEY = "organization"

    const val APIKEY_EXPIRATION_KEY = "expiration"
    const val APIKEY_KEY = "expiration"
}

fun Context.sharedPreferences(): SharedPreferences = this.getSharedPreferences(BASE_SP, Context.MODE_PRIVATE)!!
fun SharedPreferences.vsdAddress() : String? {
    val address = this.getString(CURRENTADDRESS, null)
    val port = this.getInt(CURRENTPORT, PORTDEFAULT)

    if(address != null)
        return "$address:$port"

    return null
}

fun SharedPreferences.checkAuthSet() =
        this.getString(USERNAME_KEY, null) != null &&
        this.getString(PASSWORD_KEY, null) != null &&
        this.getString(ORGANIZATION_KEY, null) != null

fun SharedPreferences.organization() : String? = this.getString(ORGANIZATION_KEY, null)

fun SharedPreferences.baseAuthorization() : String? {
    val username =  this.getString(USERNAME_KEY, null)
    val password = this.getString(PASSWORD_KEY, null)
    return getEncodedAuthorization(username, password)
}

fun SharedPreferences.apiAuthorization() : String? {
    val expiration = this.getLong(APIKEY_EXPIRATION_KEY, -1)
    if(System.currentTimeMillis() > expiration)
        return null

    val username = this.getString(USERNAME_KEY, null)
    val apikey = this.getString(APIKEY_KEY, null)
    return getEncodedAuthorization(username, apikey)
}

private fun getEncodedAuthorization(username: String?, key: String?) : String?{

    if(username == null || key == null)
        return null

    val auth = "$username:$key".toByteArray(Charsets.UTF_8)
    val encoded = Base64.encode(auth, Base64.DEFAULT)
    return String(encoded)
}
