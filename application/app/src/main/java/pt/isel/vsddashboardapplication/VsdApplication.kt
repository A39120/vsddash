package pt.isel.vsddashboardapplication

import android.app.Application
import android.util.Log
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import pt.isel.vsddashboardapplication.communication.services.model.Session
import pt.isel.vsddashboardapplication.utils.sharedPreferences
import pt.isel.vsddashboardapplication.utils.vsdAddress
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

class VsdApplication : Application(){
    companion object {
        private const val TAG = "APP"
    }

    private var vsdClient : Retrofit? = null
    private var session : Session? = null
    private var client : Retrofit? = null


    override fun onCreate() {
        super.onCreate()
        Log.v(TAG, "Creating application")
        val sp = this.sharedPreferences()

        val address = sp.vsdAddress()
        if(address != null)
            setVsdClient(address)
    }

    fun getVsdClient() : Retrofit? = vsdClient

    fun setVsdClient(address: String) {
        try {
            this.vsdClient = Retrofit.Builder()
                .baseUrl(address)
                .addCallAdapterFactory(CoroutineCallAdapterFactory())
                .addConverterFactory(MoshiConverterFactory.create())
                .build()


        } catch (ex: Throwable) {}
    }

    fun setSession(session: Session){
        this.session = session
    }


}

fun Application.vsdclient() =
    (this as VsdApplication).getVsdClient()
