package pt.isel.vsddashboardapplication

import org.junit.Assert
import org.junit.Test
//import ua.naiksoftware.stomp.Stomp
//import ua.naiksoftware.stomp.StompClient
//import ua.naiksoftware.stomp.dto.StompHeader

class WebSocketTest(
) {
/*
    //private val uri = "wss://echo.websocket.org"
    fun connect(header: List<StompHeader>? = null): StompClient{
        val addr = "ws://192.168.1.73:8080/vsdapi/websocket"
        val wsClient = Stomp.over(Stomp.ConnectionProvider.OKHTTP, addr)
        wsClient.withClientHeartbeat(1000)
        Assert.assertNotNull(wsClient)
        wsClient.connect(header)
        return wsClient
    }

    @Test
    fun connectToUri() {
        val wsClient = connect()
        // Wait for connection
        while(!wsClient.isConnected){}

        Assert.assertTrue(wsClient.isConnected)
    }

    @Test
    fun subscribe(){
        val headers = listOf(
            StompHeader("username", "admin"))

        val wsClient = connect(headers)

        println("Starting flow")
        val flow = wsClient.topic("/queue/reply" )

        println("Sending message")
        val error = wsClient.send("/message", "Message 1").blockingGet()
        println("Received message")

        error?: print(error?.message)

        println("Waiting for response")
        //val message = flow.blockingFirst()
        //println(message)
    }

    @Test
    fun login(){
        listOf(
            StompHeader("user", "")
        )
        val wsClient = connect()

        okhttp3.
        wsClient.send("/login",
                "{\"username\":\"admin\"," +
                    "\"password\":\"password\","+
                    "\"organization\":\"csp\", "+
                    "\"address\":\"address\"}"
        ).blockingGet()

        Thread.sleep(10000)
    }
    */
}