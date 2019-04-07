package pt.isel.vsddashboardapplication.communication.websockets

import android.util.Log
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.OnLifecycleEvent
import dagger.Provides
import ua.naiksoftware.stomp.Stomp
import ua.naiksoftware.stomp.StompClient
import javax.inject.Singleton

object WebSocketClient : LifecycleObserver{
    private const val TAG = "WSClient"
    private var wsClient : StompClient? = null

    fun init(address: String?){
        Log.v(TAG, "Connecting to $address")
        address?.let {
            wsClient = Stomp.over(Stomp.ConnectionProvider.OKHTTP, it)
            wsClient?.connect()
        }
    }

    @Provides
    @Singleton
    fun getClient() = wsClient

    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
    fun onStop(){
        wsClient?.disconnect()
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    fun onResume(){
        Log.v(TAG, "Stopping web socket")
        wsClient?.reconnect()
    }

}