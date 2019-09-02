package pt.isel.vsddashboardapplication.model.events

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import pt.isel.vsddashboardapplication.model.*


@JsonClass(generateAdapter = false)
data class Event(
    @Json(name = "entities") val entities: List<Any>? = listOf(),
    @Json(name = "entityType") val entityType: String? = "",
    @Json(name = "eventReceivedTime") val eventReceivedTime: Long? = 0,
    @Json(name = "type") val type: String? = "",
    @Json(name = "userName") val userName: String? = ""
)
/*
{
    data class EventJson(
        var entities : List<Any>? = listOf(),
        val entityType: String? = "",
        val eventReceivedTime: Long? = 0,
        val type: String? = "",
        val userName: String? = ""
    )
}
*/
