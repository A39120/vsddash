package pt.isel.vsddashboardapplication.communication

import okhttp3.WebSocket

class WebSocketCloseHandler(private val ws: WebSocket) {

    fun close(){
        ws.close(1000, "websocket close")
    }

}