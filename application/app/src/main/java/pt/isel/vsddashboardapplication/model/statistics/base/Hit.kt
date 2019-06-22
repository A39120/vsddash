package pt.isel.vsddashboardapplication.model.statistics.base


import com.squareup.moshi.Json

data class Hit<T>(
    @Json(name = "_id") val id: String? = "",
    @Json(name = "_index") val index: String? = "",
    @Json(name = "_score") val score: Double? = 0.0,
    @Json(name = "_source") val source: T? = null,
    @Json(name = "_type") val type: String? = ""
)