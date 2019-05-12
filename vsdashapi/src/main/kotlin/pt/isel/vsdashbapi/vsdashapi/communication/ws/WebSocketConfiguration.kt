package pt.isel.vsdashbapi.vsdashapi.communication.ws

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Configuration
import org.springframework.messaging.simp.config.MessageBrokerRegistry
import org.springframework.web.socket.config.annotation.*
import pt.isel.vsdashbapi.vsdashapi.authentication.VsdUser.VsdSessionMapper

@Configuration
@EnableWebSocketMessageBroker
open class WebSocketConfiguration : WebSocketMessageBrokerConfigurer {


    override fun registerStompEndpoints(registry: StompEndpointRegistry){
        registry.addEndpoint("/app")
                .setHandshakeHandler(WebSocketHandshakeHandler())
                .withSockJS()                   //It appears to only work with sock js
                //.addInterceptors(HandshakeInterceptorImpl())
                //.setAllowedOrigins("*")
    }

    override fun configureMessageBroker(registry: MessageBrokerRegistry) {
        registry.enableSimpleBroker("/topic", "/queue")
        registry.setApplicationDestinationPrefixes("/api")
    }

}