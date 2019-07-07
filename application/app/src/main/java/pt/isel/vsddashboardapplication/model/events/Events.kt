package pt.isel.vsddashboardapplication.model.events
import com.squareup.moshi.Json


data class Events<T>(
    @Json(name = "events") val events: List<Event<T>?> = listOf(),
    @Json(name = "uuid") val uuid: String? = ""
)