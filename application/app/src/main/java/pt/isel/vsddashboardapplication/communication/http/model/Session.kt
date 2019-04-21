package pt.isel.vsddashboardapplication.communication.http.model

data class Session(
    val token: String,
    val timeout: Long
)