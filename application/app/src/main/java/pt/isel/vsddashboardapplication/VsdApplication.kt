package pt.isel.vsddashboardapplication

import android.util.Log
import dagger.android.DaggerApplication
import pt.isel.vsddashboardapplication.injection.DaggerAppComponent
import pt.isel.vsddashboardapplication.utils.SessionContainer

class VsdApplication : DaggerApplication(){

    override fun applicationInjector()  = DaggerAppComponent.builder().application(this).build()

    companion object {
        private const val TAG = "APP"
    }

    val session = SessionContainer()

    override fun onCreate() {
        super.onCreate()
        Log.d(TAG, "Creating application")
    }

}
