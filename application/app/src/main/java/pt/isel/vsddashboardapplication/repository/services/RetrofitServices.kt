package pt.isel.vsddashboardapplication.repository.services

import okhttp3.Credentials
import pt.isel.vsddashboardapplication.communication.AuthenticationInterceptor
import pt.isel.vsddashboardapplication.communication.provider.HttpClientBuilderProvider
import retrofit2.Retrofit
import pt.isel.vsddashboardapplication.communication.provider.RetrofitBuilderProvider
import pt.isel.vsddashboardapplication.repository.services.vsd.AuthenticationService

/**
 * Contains services related to retrofit
 */
class RetrofitServices
    private constructor(private val api: String,
                        private val username: String,
                        private val organization: String)
{
    companion object {
        private var services: RetrofitServices? = null

        fun getInstance(api: String? = null, username: String? = null, organization: String? = null): RetrofitServices {
            if(services != null)
                return services!!

            if(api == null || username == null || organization == null)
                throw IllegalArgumentException()

            services = RetrofitServices(api, username, organization)
            return services!!
        }

    }

    private var authenticationService : AuthenticationService? = null
    private var retrofitVsdApi: Retrofit? = null

    fun  createAuthenticationService(password: String) :
            AuthenticationService? {

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

        authenticationService = builder
            .client(httpClient)
            .build()
            .create(AuthenticationService::class.java)

        return authenticationService
    }

    fun <T> createService(
        klass: Class<T>,
        password: String?) : T? {

        if(retrofitVsdApi == null) {
            val builder: Retrofit.Builder by lazy {
                RetrofitBuilderProvider
                    .getBuilder()
                    .baseUrl(api)
            }

            val authToken = Credentials.basic(username, password?:"")
            val httpClient = HttpClientBuilderProvider
                .getClient()
                .addInterceptor( AuthenticationInterceptor( authToken, organization ) )
                .build()

            retrofitVsdApi = builder.client(httpClient).build()
        }

        return createVsdService(klass)
    }

    fun <T> createVsdService(klass: Class<T>) : T? = retrofitVsdApi?.create(klass)

}