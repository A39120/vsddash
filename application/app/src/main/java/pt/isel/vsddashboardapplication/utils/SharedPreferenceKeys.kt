package pt.isel.vsddashboardapplication.utils

import android.content.Context
import android.content.SharedPreferences
import pt.isel.vsddashboardapplication.utils.SharedPreferenceKeys.BASE_SP


object SharedPreferenceKeys {
    const val BASE_SP = "vsdsharedpreferences"


    const val CURRENTIPKEY = "currentip"
    const val IPDEFAULT = "127.0.0.1"
    const val CURRENTPORT = "currentport"
    const val PORTDEFAULT = 1433

    const val CURRENTADDRESS = "currentaddress"
    const val DEFAULTADDRESS = "127.0.0.1:8443"
}

fun Context.sharedPreferences() = this.getSharedPreferences(BASE_SP, Context.MODE_PRIVATE)