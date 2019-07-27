package pt.isel.vsddashboardapplication.repository.services.vsd

import kotlinx.coroutines.Deferred
import pt.isel.vsddashboardapplication.model.VSP
import retrofit2.http.GET
import retrofit2.http.Headers

interface VspService {

    @Headers("Accept: application/json")
    @GET("/nuage/api/v5_0/vsps")
    fun getVsps() : Deferred<List<VSP>?>

}