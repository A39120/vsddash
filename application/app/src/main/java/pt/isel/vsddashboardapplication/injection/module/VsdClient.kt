package pt.isel.vsddashboardapplication.injection.module

import android.annotation.SuppressLint
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import java.security.KeyManagementException
import java.security.NoSuchAlgorithmException
import java.security.SecureRandom
import java.security.cert.X509Certificate
import javax.net.ssl.*

object VsdClient {

    fun  getClient() : OkHttpClient {
        val builder = OkHttpClient.Builder()
        val logging = HttpLoggingInterceptor()
        logging.level = HttpLoggingInterceptor.Level.BODY
        try {
            val trustAllCerts: Array<TrustManager> = arrayOf(object: X509TrustManager{
                @SuppressLint("TrustAllX509TrustManager")
                override fun checkClientTrusted(chain: Array<out X509Certificate>?, authType: String?) { }
                @SuppressLint("TrustAllX509TrustManager")
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
        builder.addInterceptor(logging)
        return builder.build()
    }

}