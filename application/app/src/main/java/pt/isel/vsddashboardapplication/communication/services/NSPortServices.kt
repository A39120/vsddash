package pt.isel.vsddashboardapplication.communication.services

import pt.isel.vsddashboardapplication.repository.pojo.NSPort
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path

interface NSPortServices {

    @Headers("Accept: application/json")
    @GET("/nuage/api/v5_0/nsgateways/{id}/nsports")
    suspend fun getGatewayPorts(@Path("id") nsgId: String) : List<NSPort>?

    @Headers("Accept: application/json")
    @GET("/nuage/api/v5_0/nsports/{id}")
    suspend fun getPort(@Path("id") portId : String) : NSPort
}
