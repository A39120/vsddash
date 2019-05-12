package pt.isel.vsdashbapi.vsdashapi.authentication.VsdUser

import net.nuagenetworks.vspk.v5_0.Me
import net.nuagenetworks.vspk.v5_0.VSDSession
import org.springframework.stereotype.Component

@Component
object LoginComponent {

    fun login(api: String, organization: String, username: String, password: String): VSDSession {
        val session = VSDSession(username, password, organization, api)
        session.start()
        return session
    }

    fun login(api: String, organization: String, username: String, certificate: String, privateKey: String): VSDSession {
        val session = VSDSession(username, organization, api, certificate, privateKey)
        session.start()
        return session
    }

}