package pt.isel.vsddashboardapplication

import android.app.Application
import android.util.Log
import pt.isel.vsddashboardapplication.communication.services.model.Session

class VsdApplication : Application(){
    companion object {
        private const val TAG = "APP"
    }

    var session : Session? = null

    override fun onCreate() {
        super.onCreate()
        Log.v(TAG, "Creating application")
    }

}
