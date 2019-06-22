package pt.isel.vsddashboardapplication.repository.pojo.statistics.base


import com.squareup.moshi.Json

data class Hits<T>(
    @Json(name = "hits") val hits: List<Hit<T>> = listOf(),
    @Json(name = "max_score") val maxScore: Double? = 0.0,
    @Json(name = "total") val total: Int? = 0
)