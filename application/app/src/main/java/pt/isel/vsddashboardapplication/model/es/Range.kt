package pt.isel.vsddashboardapplication.model.es

import com.squareup.moshi.Json

data class Range(
    @Json(name = "timestamp") val timestamp: GenericRange
)