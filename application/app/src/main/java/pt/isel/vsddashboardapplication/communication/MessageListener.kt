package pt.isel.vsddashboardapplication.communication

interface MessageListener {

    fun onMessage(message : StompMessage?)

}