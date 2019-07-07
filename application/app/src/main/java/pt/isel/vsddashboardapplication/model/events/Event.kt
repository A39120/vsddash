package pt.isel.vsddashboardapplication.model.events


import com.squareup.moshi.Json

data class Event<T>(
    @Json(name = "entities") val entities: List<Any?>? = listOf(),
    @Json(name = "entityType") val entityType: String? = "",
    @Json(name = "eventReceivedTime") val eventReceivedTime: Long? = 0,
    @Json(name = "type") val type: String? = "",
    @Json(name = "userName") val userName: String? = ""
)