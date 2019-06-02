package pt.isel.vsddashboardapplication.communication.services

import kotlinx.coroutines.Deferred
import pt.isel.vsddashboardapplication.communication.services.model.Session
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Headers

interface AuthenticationService {

    @Headers("Accept: application/json")
    @GET("/nuage/api/v5_0/me")
    fun authenticate(
        @Header("Authorization") authorization: String,
        @Header("X-Nuage-Organization") organization: String
    ) : Deferred<List<Session>>

    @Headers("Accept: application/json")
    @GET("/nuage/api/v5_0/me")
    fun authenticate() : Deferred<List<Session>>
}




