package pt.isel.vsddashboardapplication.model.statistics.inner


import com.squareup.moshi.Json

data class Disk(
    @Json(name = "available")
    val available: Int? = 0,
    @Json(name = "name")
    val name: String? = "",
    @Json(name = "total")
    val total: Int? = 0,
    @Json(name = "used")
    val used: Int? = 0
)