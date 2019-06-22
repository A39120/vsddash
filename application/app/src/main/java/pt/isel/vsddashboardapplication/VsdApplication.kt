package pt.isel.vsddashboardapplication

import android.util.Log
import dagger.android.DaggerApplication
import pt.isel.vsddashboardapplication.injection.DaggerAppComponent
import pt.isel.vsddashboardapplication.model.Session

class VsdApplication : DaggerApplication(){

    override fun applicationInjector()  = DaggerAppComponent.builder().application(this).build()

    companion object {
        private const val TAG = "APP"
    }

    var session : Session? = null

    override fun onCreate() {
        super.onCreate()
        Log.v(TAG, "Creating application")
    }

}
