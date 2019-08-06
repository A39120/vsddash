package pt.isel.vsddashboardapplication.model.events


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import pt.isel.vsddashboardapplication.model.BaseEvent

data class Event(
    @Json(name = "entities") val entities: List<BaseEvent?>? = listOf(),
    @Json(name = "entityType") val entityType: String? = "",
    @Json(name = "eventReceivedTime") val eventReceivedTime: Long? = 0,
    @Json(name = "type") val type: String? = "",
    @Json(name = "userName") val userName: String? = ""
)
