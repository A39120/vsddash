package pt.isel.vsddashboardapplication.model

import com.squareup.moshi.Json

data class BaseEvent(
    @Json(name = "ID") val ID: String = ""
)
