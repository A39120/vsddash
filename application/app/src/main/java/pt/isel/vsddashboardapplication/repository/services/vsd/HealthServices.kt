package pt.isel.vsddashboardapplication.repository.services.vsd

import kotlinx.coroutines.Deferred
import pt.isel.vsddashboardapplication.model.Health
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface HealthServices {

    @Headers("Accept: application/json")
    @GET("/nuage/health")
    fun getHealth() : Deferred<List<Health>?>

    @Headers("Accept: application/json")
    @GET("/nuage/health")
    fun getHealth(@Query("component") component: String?) : Deferred<List<Health>?>

}