package pt.isel.vsddashboardapplication.repository.pojo.statistics.inner


import com.squareup.moshi.Json

data class Action(
    @Json(name = "aclId")
    val aclId: String? = "",
    @Json(name = "type")
    val type: String? = "",
    @Json(name = "value")
    val value: String? = ""
)