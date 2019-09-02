package pt.isel.vsddashboardapplication.communication.provider

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import java.security.KeyManagementException
import java.security.NoSuchAlgorithmException
import java.security.SecureRandom
import java.security.cert.X509Certificate
import java.util.concurrent.TimeUnit
import javax.net.ssl.*

object HttpClientBuilderProvider {

    /**
     * Provides OkHttpClient builder with set of predefined
     * settings
     */
    fun  getClient() : OkHttpClient.Builder {
        val builder = OkHttpClient.Builder()
        val logging = HttpLoggingInterceptor()
        logging.level = HttpLoggingInterceptor.Level.BODY
        try {
            val trustAllCerts: Array<TrustManager> = arrayOf(object: X509TrustManager{
                override fun checkClientTrusted(chain: Array<out X509Certificate>?, authType: String?) { }
                override fun checkServerTrusted(chain: Array<out X509Certificate>?, authType: String?) { }
                override fun getAcceptedIssuers(): Array<X509Certificate> = arrayOf()
            })
            val sc = SSLContext.getInstance("SSL")
            val keyManagers: Array<KeyManager> = arrayOf()

            sc.init(keyManagers, trustAllCerts, SecureRandom())
            builder.sslSocketFactory(sc.socketFactory, trustAllCerts[0] as X509TrustManager)
            builder.hostnameVerifier { _, _ -> true }
        }
        catch (ex: NoSuchAlgorithmException) { }
        catch (ex: KeyManagementException) { }
        builder
            .addInterceptor(logging)
            .readTimeout(30, TimeUnit.SECONDS)
            .retryOnConnectionFailure(true)

        return builder
    }

}