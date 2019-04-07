package pt.isel.vsddashboardapplication

import org.junit.Assert
import org.junit.Test
import ua.naiksoftware.stomp.Stomp

class WebSocketTest(
) {

    //private val uri = "wss://echo.websocket.org"

    @Test
    fun connectToUri(){
        val addr = "ws://192.168.1.73:8080/vsdapi/websocket"
        val wsClient = Stomp.over(Stomp.ConnectionProvider.OKHTTP, addr)
        wsClient.withClientHeartbeat(1000)
        Assert.assertNotNull(wsClient)
        wsClient.connect()
        Assert.assertTrue(wsClient.isConnected)

        Thread.sleep(10000)

    }
}