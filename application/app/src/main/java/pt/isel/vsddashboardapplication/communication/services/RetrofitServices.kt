package pt.isel.vsddashboardapplication.communication.services

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.Credentials
import pt.isel.vsddashboardapplication.communication.AuthenticationInterceptor
import pt.isel.vsddashboardapplication.communication.BaseHttpClient
import pt.isel.vsddashboardapplication.model.converters.BootstapStatusAdapter
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit
import pt.isel.vsddashboardapplication.communication.NullOnEmptyConverterFactory

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

        val moshi = Moshi.Builder()
            .add(KotlinJsonAdapterFactory())
            .build()


        val builder: Retrofit.Builder by lazy {
            Retrofit.Builder()
                .baseUrl(api)
                .addCallAdapterFactory(CoroutineCallAdapterFactory())
                .addConverterFactory(MoshiConverterFactory.create(moshi))
        }

        val authToken = Credentials.basic(username, password)
        val httpClient = BaseHttpClient
            .getClient()
            .addInterceptor(
                AuthenticationInterceptor(
                    authToken,
                    organization
                )
            )
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
            val moshi = Moshi.Builder()
                .add(KotlinJsonAdapterFactory())
                .add(BootstapStatusAdapter())
                .build()


            val builder: Retrofit.Builder by lazy {
                Retrofit.Builder()
                    .baseUrl(api)
                    .addCallAdapterFactory(CoroutineCallAdapterFactory())
                    .addConverterFactory(NullOnEmptyConverterFactory())
                    .addConverterFactory(MoshiConverterFactory.create(moshi))
            }

            val authToken = Credentials.basic(username, password?:"")
            val httpClient = BaseHttpClient
                .getClient()
                .addInterceptor( AuthenticationInterceptor( authToken, organization ) )
                .readTimeout(30, TimeUnit.SECONDS)
                .retryOnConnectionFailure(true)
                .build()

            retrofitVsdApi = builder.client(httpClient).build()
        }

        return createVsdService(klass)
    }

    fun <T> createVsdService(klass: Class<T>) : T? = retrofitVsdApi?.create(klass)

}