package pt.isel.vsddashboardapplication

import android.util.Log
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication
import dagger.android.HasActivityInjector
import pt.isel.vsddashboardapplication.injection.DaggerAppComponent
import pt.isel.vsddashboardapplication.injection.module.ApplicationModule
import pt.isel.vsddashboardapplication.utils.SessionContainer
import javax.inject.Inject

class VsdApplication : DaggerApplication(), HasActivityInjector{
    companion object {
        private const val TAG = "APP"
    }

    override fun applicationInjector(): AndroidInjector<out DaggerApplication> = applicationInjector

    var applicationInjector: AndroidInjector<out DaggerApplication> =  DaggerAppComponent
        .builder()
        .applicationModule(ApplicationModule(this))
        .build()


    val session = SessionContainer()

    override fun onCreate() {
        super.onCreate()
        Log.d(TAG, "Creating application")
    }



}
