package pt.isel.vsddashboardapplication.repository.services.vsd

import kotlinx.coroutines.Deferred
import pt.isel.vsddashboardapplication.model.Alarm
import pt.isel.vsddashboardapplication.model.NSGInfo
import pt.isel.vsddashboardapplication.model.NSGateway
import pt.isel.vsddashboardapplication.model.NSPort
import pt.isel.vsddashboardapplication.model.events.Events
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path

interface NSGatewayService {

    @Headers("Accept: application/json")
    @GET("/nuage/api/v5_0/enterprises/{enterprise}/nsgateways")
    fun getGateways(@Path("enterprise")enterprise: String) : Deferred<List<NSGateway>?>

    @Headers("Accept: application/json")
    @GET("/nuage/api/v5_0/nsgateways/{id}")
    fun getGateway(@Path("id") nsgId: String) : Deferred<List<NSGateway>?>

    @Headers("Accept: application/json")
    @GET("/nuage/api/v5_0/nsgateways/{id}/nsginfos")
    fun getGatewayInfo(@Path("id") nsgId: String) : Deferred<List<NSGInfo>?>

    @Headers("Accept: application/json")
    @GET("/nuage/api/v5_0/nsgateways/{id}/nsports")
    fun getGatewayPorts(@Path("id") nsgId: String) : Deferred<List<NSPort>?>

    @Headers("Accept: application/json")
    @GET("/nuage/api/v5_0/nsgateways/{id}/alarms")
    fun getGatewayAlarms(@Path("id") nsgId: String) : Deferred<List<Alarm>?>

}


