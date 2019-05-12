package pt.isel.vsdashbapi.vsdashapi.communication.http.client

import net.nuagenetworks.bambou.ssl.NaiveHostnameVerifier
import net.nuagenetworks.bambou.ssl.X509NaiveTrustManager
import org.slf4j.LoggerFactory
import org.springframework.core.ParameterizedTypeReference
import org.springframework.http.*
import pt.isel.vsdashbapi.vsdashapi.communication.http.client.pojo.user.User
import java.security.KeyManagementException
import java.security.NoSuchAlgorithmException
import java.security.SecureRandom
import javax.net.ssl.HttpsURLConnection
import javax.net.ssl.KeyManager
import javax.net.ssl.SSLContext
import javax.net.ssl.TrustManager
import org.springframework.web.client.RestTemplate
import pt.isel.vsdashbapi.vsdashapi.communication.exceptions.InvalidLoginException

/**
 * Represents a http request
 */
object HttpRequest {

    private val logger = LoggerFactory.getLogger(HttpRequest::class.java)

    fun makeRequest(){

    }

    /**
     * Prepares to accept the self-signed certificates
     */
    private fun prepareSSLAuthentication() {
        try {
            val trustAllCerts: Array<TrustManager> = arrayOf(X509NaiveTrustManager())
            val sc = SSLContext.getInstance("SSL")
            val keyManagers: Array<KeyManager> = arrayOf()

            sc.init(keyManagers, trustAllCerts, SecureRandom())
            HttpsURLConnection.setDefaultSSLSocketFactory(sc.socketFactory)
            val allHostsValid = NaiveHostnameVerifier()
            HttpsURLConnection.setDefaultHostnameVerifier(allHostsValid)
        } catch (ex: NoSuchAlgorithmException) {
            logger.error("Error", ex)
        } catch (ex: KeyManagementException) {
            logger.error("Error", ex)
        }
    }

    fun connect(org: String, username: String, password: String, api: String) : User {

        // Make the request and map it to User
        val response = get(org, username, password) { template, entity ->
            template.exchange("$api/nuage/api/v5_0/me",
                    HttpMethod.GET,
                    entity,
                    object : ParameterizedTypeReference<List<User>>() {}
            )
        }

        // Return response
        when(response.statusCode){
            HttpStatus.FORBIDDEN -> throw InvalidLoginException("Failed to authenticate user")
            HttpStatus.OK -> {
                if (response.body == null || !response.hasBody())
                    throw InvalidLoginException("Failed to authenticate user")

                return response.body!!.first()
            }
            else -> throw IllegalArgumentException()
        }
    }

    fun <R> get(org: String, username: String, apiKey: String, exchangeFunction: (RestTemplate, HttpEntity<String>) -> ResponseEntity<R?>) : ResponseEntity<R?> {
        HttpRequest.prepareSSLAuthentication()

        // Necessary Nuage Headers - X-Nuage-Organization
        val headers = HttpHeaders()
        headers[NuageHeaders.ORGANIZATION] = org
        headers.setBasicAuth(username, apiKey)

        val entity = HttpEntity<String>(headers)

        // Make the request
        val restTemplate = RestTemplate()
        return exchangeFunction(restTemplate, entity)
    }

}