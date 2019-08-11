package pt.isel.vsddashboardapplication.repository.services.vsd

import kotlinx.coroutines.Deferred
import pt.isel.vsddashboardapplication.model.Alarm
import pt.isel.vsddashboardapplication.model.VPort
import pt.isel.vsddashboardapplication.model.VRS
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path

interface VrsService {

    @Headers("Accept: application/json")
    @GET("/nuage/api/v5_0/vrss/{id}")
    fun getVrs(@Path("id") parentId: String) : Deferred<List<VRS>?>

    @Headers("Accept: application/json")
    @GET("/nuage/api/v5_0/vrss/")
    fun getGlobalVrss() : Deferred<List<VRS>?>

    @Headers("Accept: application/json")
    @GET("/nuage/api/v5_0/vscs/{id}/vrss")
    fun getVscVrs(@Path("id") parentId: String) : Deferred<List<VRS>?>

    @Headers("Accept: application/json")
    @GET("/nuage/api/v5_0/vrss/{id}/alarms")
    fun getVrsAlarms(@Path("id") parentId: String) : Deferred<List<Alarm>?>

    @Headers("Accept: application/json")
    @GET("/nuage/api/v5_0/vrss/{id}/vports")
    fun getVrsVports(@Path("id") parentId: String) : Deferred<List<VPort>?>
}