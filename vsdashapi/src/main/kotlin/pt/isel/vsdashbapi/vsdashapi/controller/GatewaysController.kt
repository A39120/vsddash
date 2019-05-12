package pt.isel.vsdashbapi.vsdashapi.controller

import net.nuagenetworks.vspk.v5_0.Gateway
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.messaging.handler.annotation.MessageMapping
import org.springframework.messaging.handler.annotation.Payload
import org.springframework.messaging.simp.annotation.SendToUser
import org.springframework.stereotype.Controller
import pt.isel.vsdashbapi.vsdashapi.authentication.VsdUser.VsdSessionMapper
import pt.isel.vsdashbapi.vsdashapi.communication.ws.VsdPrincipal
import java.security.Principal

@Controller
class GatewaysController {

    @MessageMapping("/gateways")
    @SendToUser("/topic/gateways")
    fun getGateways(@Payload message: String?, principal: Principal) : List<Gateway>? {
        val user = VsdSessionMapper.getSession(principal.name)
        //val user = (principal as VsdPrincipal).getUser()
        val gatewaysFetcher = user?.me?.gateways
        val gateways = gatewaysFetcher?.get()
        return gateways
    }


}