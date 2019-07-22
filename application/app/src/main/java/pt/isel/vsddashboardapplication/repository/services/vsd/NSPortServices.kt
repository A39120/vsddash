package pt.isel.vsddashboardapplication.repository.services.vsd

import kotlinx.coroutines.Deferred
import pt.isel.vsddashboardapplication.model.NSPort
import pt.isel.vsddashboardapplication.model.events.Events
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path

interface NSPortServices {

    @Headers("Accept: application/json")
    @GET("/nuage/api/v5_0/nsgateways/{id}/nsports")
    fun getGatewayPorts(@Path("id") nsgId: String) : Deferred<List<NSPort>?>

    @Headers("Accept: application/json")
    @GET("/nuage/api/v5_0/nsports/{id}")
    fun getPort(@Path("id") portId : String) : Deferred<List<NSPort>?>


}
