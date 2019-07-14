package pt.isel.vsddashboardapplication.repository.services

import android.util.Log
import okhttp3.Credentials
import pt.isel.vsddashboardapplication.communication.AuthenticationInterceptor
import pt.isel.vsddashboardapplication.communication.provider.HttpClientBuilderProvider
import retrofit2.Retrofit
import pt.isel.vsddashboardapplication.communication.provider.RetrofitBuilderProvider
import pt.isel.vsddashboardapplication.repository.services.vsd.AuthenticationService

/**
 * Contains services related to retrofit
 */
class RetrofitServices constructor(
        private val api: String,
        private val username: String,
        private val organization: String) {
    companion object {
        private const val TAG = "RETROFIT"
    }

    /**
     * Creates the authentication service
     */
    fun  getRetrofit(password: String) : Retrofit? {
        val builder: Retrofit.Builder by lazy {
            RetrofitBuilderProvider
                .getBuilder()
                .baseUrl(api)
        }

        val authToken = Credentials.basic(username, password)
        val httpClient = HttpClientBuilderProvider
            .getClient()
            .addInterceptor(AuthenticationInterceptor( authToken, organization ))
            .build()
        Log.d(TAG, "Creating Retrofit using credentials: Authorization = $authToken, organization= $organization")

        return builder
            .client(httpClient)
            .build()
    }

}