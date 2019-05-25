package pt.isel.vsdashbapi.vsdashapi

import org.junit.Assert
import org.springframework.http.HttpHeaders
import org.springframework.messaging.converter.MappingJackson2MessageConverter
import org.springframework.messaging.simp.stomp.StompSession
import org.springframework.util.CollectionUtils
import org.springframework.util.concurrent.ListenableFuture
import org.springframework.web.socket.WebSocketHttpHeaders
import org.springframework.web.socket.client.standard.StandardWebSocketClient
import org.springframework.web.socket.messaging.WebSocketStompClient
import org.springframework.web.socket.sockjs.client.SockJsClient
import org.springframework.web.socket.sockjs.client.Transport
import org.springframework.web.socket.sockjs.client.WebSocketTransport
import java.util.*
import java.util.concurrent.atomic.AtomicReference

object Utils {

    val address = "ws://127.0.0.1:8080/app/"

    fun connect(URL: String, username: String, password: String, organization: String): ListenableFuture<StompSession> {
        val failure = AtomicReference<Throwable?>()
        val result = AtomicReference<Boolean?>()
        val list = AtomicReference(LinkedList<String>())

        val basedAuth = Base64.getEncoder().encode("$username:$password".toByteArray(Charsets.UTF_8))
        val map = CollectionUtils.toMultiValueMap(HashMap<String, List<String>>())
        map["Organization"] = listOf(organization)
        map["API-Address"] = listOf(URL)
        map["Authorization"] = listOf(basedAuth.toString(Charsets.UTF_8))

        val headers = WebSocketHttpHeaders(HttpHeaders(map))
        val transports = ArrayList<Transport>()
        val client = StandardWebSocketClient()

        transports.add(WebSocketTransport(client))
        val sockJsClient = SockJsClient(transports)
        val stompClient = WebSocketStompClient(sockJsClient)
        stompClient.messageConverter = MappingJackson2MessageConverter()

        val sessionHandler = TestSessionHandler(failure, result, list)
        val session = stompClient.connect(address, headers, sessionHandler)

        while (result.get() == null) { }

        Assert.assertTrue(result.get()!!)
        return session
    }

}