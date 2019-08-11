package pt.isel.vsddashboardapplication.repository.services.vsd

import kotlinx.coroutines.Deferred
import pt.isel.vsddashboardapplication.model.Alarm
import pt.isel.vsddashboardapplication.model.VSC
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path

interface VscService {

    @Headers("Accept: application/json")
    @GET("/nuage/api/v5_0/vsps/{id}/vscs")
    fun getVcsc(@Path("id") parentId: String) : Deferred<List<VSC>?>

    @Headers("Accept: application/json")
    @GET("/nuage/api/v5_0/vscs/{id}")
    fun getVcs(@Path("id") id: String) : Deferred<List<VSC>?>

    @Headers("Accept: application/json")
    @GET("/nuage/api/v5_0/vscs/{id}/alarms")
    fun getVcsAlarms(@Path("id") id: String) : Deferred<List<Alarm>?>

}