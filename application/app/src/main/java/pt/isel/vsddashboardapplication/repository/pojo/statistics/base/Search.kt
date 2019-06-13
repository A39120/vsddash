package pt.isel.vsddashboardapplication.repository.pojo.statistics.base


import com.squareup.moshi.Json

data class Search<T>(
    @Json(name = "hits") val hits: Hits<T> = Hits(),
    @Json(name = "_shards") val shards: Shards? = Shards(),
    @Json(name = "timed_out") val timedOut: Boolean? = false,
    @Json(name = "took") val took: Int? = 0
)