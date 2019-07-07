package pt.isel.vsddashboardapplication.utils

import android.content.Context
import android.content.SharedPreferences
import pt.isel.vsddashboardapplication.utils.SharedPreferenceKeys.BASE_SP
import pt.isel.vsddashboardapplication.utils.SharedPreferenceKeys.ORGANIZATION_KEY

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

fun SharedPreferences.organization() : String? = this.getString(ORGANIZATION_KEY, null)
