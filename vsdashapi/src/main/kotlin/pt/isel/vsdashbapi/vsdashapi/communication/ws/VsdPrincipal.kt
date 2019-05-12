package pt.isel.vsdashbapi.vsdashapi.communication.ws

import net.nuagenetworks.vspk.v5_0.Me
import net.nuagenetworks.vspk.v5_0.VSDSession
import java.security.Principal

class VsdPrincipal(private val user: VSDSession) : Principal {

    override fun getName(): String =
        user.me.apiKey

    fun getUser() = user

}