package pt.isel.vsdashbapi.vsdashapi

import org.springframework.messaging.simp.stomp.StompCommand
import org.springframework.messaging.simp.stomp.StompHeaders
import org.springframework.messaging.simp.stomp.StompSession
import org.springframework.messaging.simp.stomp.StompSessionHandlerAdapter
import java.util.*
import java.util.concurrent.atomic.AtomicReference

class TestSessionHandler(
        private val failure: AtomicReference<Throwable?>,
        private val result: AtomicReference<Boolean?>,
        private val messages: AtomicReference<LinkedList<String>>
) : StompSessionHandlerAdapter() {


    override fun handleFrame(headers: StompHeaders, payload: Any?) {
        val curr = messages.get()
        curr.push(payload.toString())
        messages.set(curr)
    }

    override fun afterConnected(session: StompSession, connectedHeaders: StompHeaders) {
        super.afterConnected(session, connectedHeaders)
        this.result.set(true)
    }

    override fun handleException(session: StompSession, c: StompCommand?, headers: StompHeaders, payload: ByteArray, exception: Throwable) {
        this.failure.set(exception)
        this.result.set(false)
    }

    override fun handleTransportError(session: StompSession, exception: Throwable) {
        this.failure.set(exception)
        this.result.set(false)
    }
}
