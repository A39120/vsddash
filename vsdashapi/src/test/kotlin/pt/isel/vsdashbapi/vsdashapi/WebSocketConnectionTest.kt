package pt.isel.vsdashbapi.vsdashapi

import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.springframework.http.HttpHeaders
import org.springframework.lang.Nullable
import org.springframework.messaging.simp.stomp.StompCommand
import org.springframework.messaging.simp.stomp.StompHeaders
import org.springframework.messaging.simp.stomp.StompSession
import org.springframework.messaging.simp.stomp.StompSessionHandlerAdapter
import org.springframework.util.CollectionUtils
import org.springframework.web.socket.WebSocketHttpHeaders
import org.springframework.web.socket.client.standard.StandardWebSocketClient
import org.springframework.web.socket.messaging.WebSocketStompClient
import org.springframework.web.socket.sockjs.client.SockJsClient
import org.springframework.web.socket.sockjs.client.Transport
import org.springframework.web.socket.sockjs.client.WebSocketTransport
import java.util.*
import java.util.concurrent.atomic.AtomicReference
import javax.net.ssl.SSLContext

class WebSocketConnectionTest{

    private val port: Int = 8080
    private lateinit var URL: String

    @Before
    fun setup() {
        URL = "ws://127.0.0.1:$port/app/"
    }

    @Test
    fun connectFail(){

        val failure = AtomicReference<Throwable?>()
        val result = AtomicReference<Boolean?>()

        val transports = ArrayList<Transport>()
        val client = StandardWebSocketClient()

        //var sslContext : SSLContext?
        //try {
        //    sslContext = SSLContext.getInstance( "TLS")
        //    sslContext.init( null, null, null) // will use java's default key and trust store which is sufficient unless you deal with self-signed certificates
        //} catch (ex : Exception) {
        //    Assert.fail()
        //}

        transports.add(WebSocketTransport(client))
        val sockJsClient = SockJsClient(transports)
        val stompClient = WebSocketStompClient(sockJsClient)

        val sessionHandler = TestSessionHandler(failure, result, AtomicReference(LinkedList()))
        stompClient.connect(URL, sessionHandler)

        while(result.get() == null) { }

        Assert.assertFalse(result.get()!!)
        Assert.assertNotNull(failure.get())
    }

    @Test
    fun connectSuccess(){
        val address = "https://124.252.253.118:8443"
        val username = "lopes"
        val password = "lopes"
        val organization = "altike"

        val basedAuth = Base64.getEncoder().encode("$username:$password".toByteArray(Charsets.UTF_8))

        val map = CollectionUtils.toMultiValueMap(HashMap<String, List<String>>())
        map["Organization"] = listOf(organization)
        map["API-Address"] = listOf(address)
        map["Authorization"] = listOf(basedAuth.toString(Charsets.UTF_8))

        val headers = WebSocketHttpHeaders(HttpHeaders(map))

        val failure = AtomicReference<Throwable?>()
        val result = AtomicReference<Boolean?>()

        val transports = ArrayList<Transport>()
        transports.add(WebSocketTransport( StandardWebSocketClient()))
        val sockJsClient = SockJsClient(transports)
        val stompClient = WebSocketStompClient(sockJsClient)

        val sessionHandler = TestSessionHandler(failure, result, AtomicReference(LinkedList()))
        stompClient.connect(URL, headers, sessionHandler)

        while(result.get() == null) { }

        Assert.assertTrue(result.get()!!)
        Assert.assertNull(failure.get())
    }

}