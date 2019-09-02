package pt.isel.vsddashboardapplication.repository.services.vsd

import kotlinx.coroutines.Deferred
import pt.isel.vsddashboardapplication.model.Health
import retrofit2.http.GET
import retrofit2.http.Headers

interface HealthServices {

    @Headers("Accept: application/json")
    @GET("/nuage/health")
    fun getHealth() : Deferred<List<Health>?>

}