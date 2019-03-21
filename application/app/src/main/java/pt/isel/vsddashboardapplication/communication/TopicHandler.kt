package pt.isel.vsddashboardapplication.communication

class TopicHandler {

    var topic = ""
    val listeners = HashSet<MessageListener>()

    fun addListener(listener: MessageListener){
        listeners.add(listener)
    }

    fun removeListener(listener: MessageListener){
        listeners.remove(listener)
    }

    fun onMessage(message: StompMessage){

    }

}