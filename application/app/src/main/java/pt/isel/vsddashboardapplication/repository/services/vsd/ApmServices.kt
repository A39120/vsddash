package pt.isel.vsddashboardapplication.repository.services.vsd

import kotlinx.coroutines.Deferred
import pt.isel.vsddashboardapplication.model.APM
import retrofit2.http.GET
import retrofit2.http.Headers

interface ApmServices {

    @Headers("Accept: application/json")
    @GET("/nuage/api/v5_0/enterprises/{enterpriseId}/applicationperformancemanagements")
    fun getApms(enterpriseId: String) : Deferred<List<APM>?>


    @Headers("Accept: application/json")
    @GET("/nuage/api/v5_0/applicationperformancemanagements/{id}")
    fun get(id: String) : Deferred<List<APM>?>

}