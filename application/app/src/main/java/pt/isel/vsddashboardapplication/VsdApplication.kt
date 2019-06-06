package pt.isel.vsddashboardapplication

import android.app.Application
import android.util.Log
import pt.isel.vsddashboardapplication.communication.services.NSGatewayService
import pt.isel.vsddashboardapplication.communication.services.RetrofitServices
import pt.isel.vsddashboardapplication.communication.services.model.Session
import pt.isel.vsddashboardapplication.utils.sharedPreferences
import retrofit2.Retrofit

class VsdApplication : Application(){
    companion object {
        private const val TAG = "APP"
    }

    //private var vsdClient : Retrofit? = null
    var session : Session? = null
    //private var client : Retrofit? = null


    override fun onCreate() {
        super.onCreate()
        Log.v(TAG, "Creating application")

    }

    //fun getVsdClient() : Retrofit? = vsdClient

}

//fun Application.vsdclient() =
//(this as VsdApplication).getVsdClient()

