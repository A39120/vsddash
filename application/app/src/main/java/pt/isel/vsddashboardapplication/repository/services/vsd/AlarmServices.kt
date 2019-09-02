package pt.isel.vsddashboardapplication.repository.services.vsd

import kotlinx.coroutines.Deferred
import pt.isel.vsddashboardapplication.model.Alarm
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path

interface AlarmServices {

    @Headers("Accept: application/json")
    @GET("/nuage/api/v5_0/nsgateways/{id}/alarms")
    fun getGatewayAlarms(@Path("id") nsgId: String) : Deferred<List<Alarm>?>?

    @Headers("Accept: application/json")
    @GET("/nuage/api/v5_0/alarms/{id}")
    fun getAlarm(@Path("id") id: String) : Deferred<List<Alarm>?>?

}