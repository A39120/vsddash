package pt.isel.vsdashbapi.vsdashapi

import net.nuagenetworks.vspk.v5_0.Gateway
import org.junit.Assert
import org.junit.Test
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder
import org.springframework.messaging.simp.stomp.StompFrameHandler
import org.springframework.messaging.simp.stomp.StompHeaders
import org.springframework.messaging.simp.stomp.StompSession
import org.springframework.util.concurrent.FailureCallback
import org.springframework.util.concurrent.ListenableFuture
import org.springframework.util.concurrent.ListenableFutureCallback
import org.springframework.util.concurrent.SuccessCallback
import pt.isel.vsdashbapi.vsdashapi.communication.message.Message
import java.lang.ref.WeakReference
import java.lang.reflect.Type
import java.util.concurrent.TimeUnit

class WSGatewaysTest {

    @Test
    fun getGatewaysTest(){
        val address = "https://124.252.253.118:8443/"
        val username = "admin"
        val password = "ObfBx9_1dUmzollR"
        val organization = "csp"

        val client = Utils.connect(address, username, password, organization)
        client.addCallback(GatewayMessanger)
        Assert.assertTrue(client.get().isConnected)

        //val rec = client.get().send("/gateways", "GET")
        while(true){}
    }

    object GatewayMessanger : ListenableFutureCallback<StompSession>{

        override fun onSuccess(result: StompSession?) {
            val sub = result!!.subscribe("/topic/gateways", GatewayFrameHandler)
            val rec = result.send("/api/gateways", "GET")
            Thread.sleep(1)
        }

        override fun onFailure(ex: Throwable) {
            Thread.sleep(1)
        }
    }

    object GatewayFrameHandler : StompFrameHandler{
        override fun handleFrame(headers: StompHeaders, payload: Any?) {
            print(payload)
        }

        override fun getPayloadType(headers: StompHeaders): Type {
            return Collection::class.java
        }

    }
}