package pt.isel.vsddashboardapplication.repository.services.vsd

import kotlinx.coroutines.Deferred
import pt.isel.vsddashboardapplication.model.Alarm
import pt.isel.vsddashboardapplication.model.VPort
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path

interface VPortService {

    @Headers("Accept: application/json")
    @GET("/nuage/api/v5_0/vrss/{id}/vports")
    fun getFromVrss(@Path("id") nsgId: String) : Deferred<List<VPort>?>

    @Headers("Accept: application/json")
    @GET("/nuage/api/v5_0/vports/{id}")
    fun getPort(@Path("id") portId : String) : Deferred<List<VPort>?>


    @Headers("Accept: application/json")
    @GET("/nuage/api/v5_0/vports/{id}/alarms")
    fun getPortAlarms(@Path("id") portId : String) : Deferred<List<Alarm>?>

}
