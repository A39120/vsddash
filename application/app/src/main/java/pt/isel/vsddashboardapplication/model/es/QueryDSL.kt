package pt.isel.vsddashboardapplication.model.es

import com.squareup.moshi.Json

data class QueryDSL(
    @Json(name = "query") val query: Query? = null
)