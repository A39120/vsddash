package pt.isel.vsdashbapi.vsdashapi.communication.http.client

import net.nuagenetworks.bambou.ssl.NaiveHostnameVerifier
import net.nuagenetworks.bambou.ssl.X509NaiveTrustManager
import net.nuagenetworks.vspk.v5_0.VRS
import org.slf4j.LoggerFactory
import org.springframework.http.HttpStatus
import org.springframework.http.HttpHeaders
import org.springframework.web.client.RestTemplate
import org.springframework.http.HttpEntity
import org.springframework.http.HttpMethod
import pt.isel.vsdashbapi.vsdashapi.communication.exceptions.InvalidLoginException
import pt.isel.vsdashbapi.vsdashapi.communication.http.client.pojo.user.User
import java.lang.IllegalArgumentException
import java.security.KeyManagementException
import java.security.NoSuchAlgorithmException
import java.security.SecureRandom
import javax.net.ssl.*
import org.springframework.core.ParameterizedTypeReference
import pt.isel.vsdashbapi.vsdashapi.communication.http.client.HttpRequest.connect


/**
 * Represents a connection to the API
 */
class UserSession(
        private val api: String,
        private val username: String,
        password: String,
        private val organization: String
) {

    /**
     * API used to make requests with
     */
    private val user : User = HttpRequest.connect(organization, username, password, api)
}