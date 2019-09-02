package pt.isel.vsddashboardapplication.repository.services.vsd

import kotlinx.coroutines.Deferred
import pt.isel.vsddashboardapplication.model.APM
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path

interface ApmServices {

    @Headers("Accept: application/json")
    @GET("/nuage/api/v5_0/enterprises/{enterpriseId}/applicationperformancemanagements")
    fun getApms(@Path("enterpriseId") enterpriseId: String) : Deferred<List<APM>?>


    @Headers("Accept: application/json")
    @GET("/nuage/api/v5_0/applicationperformancemanagements/{id}")
    fun get(@Path("id") id: String) : Deferred<List<APM>?>

}