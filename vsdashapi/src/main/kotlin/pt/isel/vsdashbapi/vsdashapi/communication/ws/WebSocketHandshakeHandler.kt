package pt.isel.vsdashbapi.vsdashapi.communication.ws

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.ComponentScan
import org.springframework.http.server.ServerHttpRequest
import org.springframework.web.socket.WebSocketHandler
import org.springframework.web.socket.server.support.DefaultHandshakeHandler
import pt.isel.vsdashbapi.vsdashapi.authentication.VsdUser.LoginComponent
import pt.isel.vsdashbapi.vsdashapi.authentication.VsdUser.VsdSessionMapper
import java.security.Principal
import java.util.*
import javax.security.auth.login.LoginException

@ComponentScan
class WebSocketHandshakeHandler : DefaultHandshakeHandler() {

    private val ORGANIZATION_KEY = "Organization"
    private val API_KEY = "API-Address"
    private val USER_KEY = "Authorization"

    /**
     * Determines user from a request.
     */
    override fun determineUser(request: ServerHttpRequest,
                               wsHandler: WebSocketHandler,
                               attributes: MutableMap<String, Any>): Principal? {
        //val oldPrincipal = super.determineUser(request, wsHandler, attributes)
        logger.debug("Adding principal")
        val headers = request.headers

        val org = headers[ORGANIZATION_KEY]
        val api = headers[API_KEY]
        val userBase64 = headers[USER_KEY]

        if (userBase64 == null || org == null || api == null ||
            userBase64.size < 1 || api.size < 1 || org.size < 1)
            throw LoginException()

        val decodedUserBytes = Base64.getDecoder().decode(userBase64.first().toByteArray(Charsets.UTF_8))
        val decodedUser = String(decodedUserBytes, Charsets.UTF_8)
        //val decodedUserBytes = String()

        //val principal = VsdPrincipal()
        val args = decodedUser.split(':')

        val username  = args.first()
        val user = when(args.size){
            2 -> {
                val password = args[1]
                LoginComponent.login(api.first(), org.first(), username, password)
            }
            3 -> {
                val cert = args[1]
                val privKey = args[2]
                LoginComponent.login(api.first(), org.first(), username, cert, privKey)
            }
            else -> throw LoginException()
        }

        user.password = null
        VsdSessionMapper.putSession(user.me.apiKey, user)
        return VsdPrincipal(user)
    }

}