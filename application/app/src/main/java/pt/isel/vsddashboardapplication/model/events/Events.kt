package pt.isel.vsddashboardapplication.model.events
import com.squareup.moshi.Json


data class Events(
    @Json(name = "events") val events: List<Event?> = listOf(),
    @Json(name = "uuid") val uuid: String? = ""
)