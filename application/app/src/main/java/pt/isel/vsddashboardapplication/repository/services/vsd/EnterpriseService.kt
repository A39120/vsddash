package pt.isel.vsddashboardapplication.repository.services.vsd

import kotlinx.coroutines.Deferred
import pt.isel.vsddashboardapplication.model.Enterprise
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path

interface EnterpriseService {

    @Headers("Accept: application/json")
    @GET("/nuage/api/v5_0/enterprises/")
    fun getEnterprises() : Deferred<List<Enterprise>?>

    @Headers("Accept: application/json")
    @GET("/nuage/api/v5_0/enterprises/{id}")
    fun getEnterprise(@Path("id") enterpriseId: String) : Deferred<Enterprise?>

}
