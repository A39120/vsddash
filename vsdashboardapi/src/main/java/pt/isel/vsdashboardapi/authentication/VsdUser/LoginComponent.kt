package pt.isel.vsdashboardapi.authentication.VsdUser

import net.nuagenetworks.vspk.v5_0.Me
import net.nuagenetworks.vspk.v5_0.VSDSession
import org.springframework.stereotype.Component
import org.springframework.web.client.HttpClientErrorException

@Component
object LoginComponent {

    fun login(api: String, organization: String, username: String, password: String): Me {
        val session = VSDSession(username, password, organization, api)
        session.start()
        return session.me
    }

    fun login(api: String, organization: String, username: String, certificate: String, privateKey: String): Me {
        val session = VSDSession(username, organization, api, certificate, privateKey)
        session.start()
        return session.me
    }

}