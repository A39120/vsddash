package pt.isel.vsddashboardapplication.model.statistics.base


import com.squareup.moshi.Json

data class Shards(
    @Json(name = "failed") val failed: Int? = 0,
    @Json(name = "successful") val successful: Int? = 0,
    @Json(name = "total") val total: Int? = 0
)