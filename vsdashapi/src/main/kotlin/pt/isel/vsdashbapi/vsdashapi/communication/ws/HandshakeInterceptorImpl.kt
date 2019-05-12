package pt.isel.vsdashbapi.vsdashapi.communication.ws

import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.server.ServerHttpRequest
import org.springframework.http.server.ServerHttpResponse
import org.springframework.web.socket.WebSocketHandler
import org.springframework.web.socket.server.HandshakeInterceptor
import pt.isel.vsdashbapi.vsdashapi.authentication.VsdUser.VsdSessionMapper
import java.lang.Exception

class HandshakeInterceptorImpl : HandshakeInterceptor {

    private val log = LoggerFactory.getLogger(HandshakeInterceptorImpl::class.java)

    override fun afterHandshake(request: ServerHttpRequest, response: ServerHttpResponse, wsHandler: WebSocketHandler, exception: Exception?) {
        log.info("Handshake done")
        if(exception != null)
            log.error("Handshake failed ${exception.message}")
    }

    override fun beforeHandshake(request: ServerHttpRequest, response: ServerHttpResponse, wsHandler: WebSocketHandler, attributes: MutableMap<String, Any>): Boolean {
        log.info("Starting handshake")
        return true
    }
}