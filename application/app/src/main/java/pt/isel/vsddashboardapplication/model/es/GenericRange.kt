package pt.isel.vsddashboardapplication.model.es

import com.squareup.moshi.Json

data class GenericRange (
    @Json(name = "gte") val gte: Long?,
    @Json(name = "lte") val lte: Long?
)