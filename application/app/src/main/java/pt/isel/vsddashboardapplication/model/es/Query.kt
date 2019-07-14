package pt.isel.vsddashboardapplication.model.es

import com.squareup.moshi.Json

data class Query(
    @Json(name = "range") val range: Range
)