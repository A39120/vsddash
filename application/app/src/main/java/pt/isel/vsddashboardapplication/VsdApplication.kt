package pt.isel.vsddashboardapplication

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import android.preference.PreferenceManager
import android.util.Log
import pt.isel.vsddashboardapplication.communication.websockets.WebSocketClient
import pt.isel.vsddashboardapplication.utils.SharedPreferenceKeys

class VsdApplication : Application(){
    companion object {
        private const val TAG = "APP"
    }

    private val wsClient = WebSocketClient

    override fun onCreate() {
        super.onCreate()
        Log.v(TAG, "Creating application")
        //val sp = PreferenceManager.getDefaultSharedPreferences(this)
        //val address = sp.getString(SharedPreferenceKeys.CURRENTADDRESS, SharedPreferenceKeys.DEFAULTADDRESS)
        //wsClient.init("ws://192.168.1.73:8080/vsdapi/websocket")
    }

    fun connectToWebServer(address: String){
        Log.v(TAG, "Connecting to new web server")
        val sp = PreferenceManager.getDefaultSharedPreferences(this)
        val editor = sp.edit()
        editor.putString(SharedPreferenceKeys.CURRENTADDRESS, address)
        editor.apply()

        wsClient.getClient()?.disconnect()
        wsClient.init(address)
    }

}

