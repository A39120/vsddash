package pt.isel.vsddashboardapplication.repository.services

import android.util.Log
import pt.isel.vsddashboardapplication.repository.services.vsd.*
import retrofit2.Retrofit

object RetrofitSingleton {
    private const val TAG = "RETROFIT"

    private var retrofitServices: RetrofitServices? = null
    private var retrofit: Retrofit? = null
    private var alarmServices: AlarmServices? = null
    private var authenticationService: AuthenticationService? = null
    private var enterpriseService: EnterpriseService? = null
    private var nsgService: NSGatewayService? = null
    private var nsportService: NSPortServices? = null
    private var eventService: EventServices? = null
    private var apmService: ApmServices? = null
    private var vrsService: VrsService? = null
    private var vscService: VscService? = null
    private var vspService: VspService? = null

    fun vrsService() : VrsService? {
        if(vrsService == null)
            vrsService = retrofit?.create(VrsService::class.java)
        return vrsService
    }

    fun vscService() : VscService? {
        if(vscService == null)
            vscService = retrofit?.create(VscService::class.java)
        return vscService
    }

    fun vspService() : VspService? {
        if(vspService == null)
            vspService = retrofit?.create(VspService::class.java)
        return vspService
    }

    fun apmServices() : ApmServices? {
        if(apmService == null)
            apmService = retrofit?.create(ApmServices::class.java)
        return apmService
    }

    fun eventServices(): EventServices? {
        if(eventService == null)
            eventService = retrofit?.create(EventServices::class.java)
        return eventService
    }

    fun alarmServices(): AlarmServices? {
        if(alarmServices == null)
            alarmServices = retrofit?.create(AlarmServices::class.java)
        return alarmServices
    }

    fun enterpriseServices(): EnterpriseService? {
        if(alarmServices == null)
            enterpriseService = retrofit?.create(EnterpriseService::class.java)
        return enterpriseService
    }

    fun nsgService(): NSGatewayService? {
        if(nsgService == null)
            nsgService = retrofit?.create(NSGatewayService::class.java)
        return nsgService
    }

    fun nsportServices(): NSPortServices? {
        if(nsportService == null)
            nsportService = retrofit?.create(NSPortServices::class.java)

        return nsportService
    }

    fun authenticationService() : AuthenticationService? {
        if(authenticationService == null)
            authenticationService = retrofit?.create(AuthenticationService::class.java)

        return authenticationService
    }

    /**
     * Prepares VSD API Service
     */
    fun prepareVsdService(
        url: String? = null,
        username: String? = null,
        organization: String? = null
    ){
        Log.d(TAG, "Setting retrofit with username $username and organization $organization ($url)")
        if(url == null || username == null || organization == null)
            return

        if(retrofitServices != null)
            reset()

        retrofitServices = RetrofitServices(url, username, organization)
    }

    /**
     * Sets up authenticator using user password
     */
    fun setupAuthenticator(password: String? = null){
        if(password==null)
            return

        authenticationService = retrofitServices
            ?.getRetrofit(password)
            ?.create(AuthenticationService::class.java)
    }

    /**
     * Sets up retrofit with the APIKey of the VSD API
     */
    fun setupRetrofit(apiKey: String? = null) {
        if(apiKey == null)
            return

        reset()
        retrofit = retrofitServices
            ?.getRetrofit(apiKey)
    }


    fun reset() {
        alarmServices = null
        nsgService = null
        enterpriseService = null
        nsportService = null
        authenticationService = null
        apmService = null
    }

}