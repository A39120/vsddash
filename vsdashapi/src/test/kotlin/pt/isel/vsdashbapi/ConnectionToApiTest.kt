package pt.isel.vsdashbapi

import org.junit.Assert
import org.junit.Test
import pt.isel.vsdashbapi.vsdashapi.communication.http.client.UserSession

public class ConnectionToApiTest {

    private val address = "https://124.252.253.58:8443"
    private val username = "admin"
    private val password = "pLrnNyMlSkveUrvV"
    private val organization = "csp"

    @Test
    fun connect(){
        val session = connectToApi()
        Assert.assertNotNull(session)
    }

    public fun connectToApi() = UserSession(address, username, password, organization)
}