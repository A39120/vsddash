package pt.isel.vsddashboardapplication.communication.services

import okhttp3.Interceptor
import okhttp3.Response

class AuthenticationInterceptor(
    private val authToken: String,
    private val organization: String
) : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val req = chain.request()
        val nReq = req
            .newBuilder()
            .header("X-Nuage-Organization", organization)
            .header("Authorization", authToken)
            .build()

        return chain.proceed(nReq)
    }


}