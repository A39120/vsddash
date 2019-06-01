package pt.isel.vsddashboardapplication.communication.services

import android.util.Base64
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import okhttp3.Credentials
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.security.auth.login.LoginException

object RetrofitServiceGenerator {

    fun  createAuthenticationService(api: String, organization: String, username: String, password: String) :
            AuthenticationService? {

        return createService(
            AuthenticationService::class.java,
            api,
            organization,
            username,
            password
        )
    }


    fun <T> createService(klass: Class<T>,
                          api: String,
                          organization: String,
                          username: String,
                          password: String) : T?{

        val builder: Retrofit.Builder by lazy {
            Retrofit.Builder()
                .baseUrl(api)
                .addCallAdapterFactory(CoroutineCallAdapterFactory())
                .addConverterFactory(MoshiConverterFactory.create())
        }

        val httpClientBuilder = OkHttpClient.Builder()
        val authToken = Credentials.basic(username,password)
        val httpClient = httpClientBuilder
            .addInterceptor(AuthenticationInterceptor(authToken, organization))
            .build()

        val retrofit = builder.client(httpClient).build()
        return retrofit.create(klass)
    }

}