package pt.isel.vsddashboardapplication.communication.http

import kotlinx.coroutines.Deferred
import pt.isel.vsddashboardapplication.communication.http.model.Session
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Headers

interface AuthenticationService {

    //@Headers("Accept: application/json")
    @GET("/nuage/api/v5_0/me")
    fun authenticate(
        @Header("Authorization") authorization: String,
        @Header("X-Nuage-Organization") organization: String
    ) : Deferred<Session>
}