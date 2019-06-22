package pt.isel.vsddashboardapplication.model.statistics


import com.squareup.moshi.Json
import pt.isel.vsddashboardapplication.model.statistics.inner.VportMetricInfo

data class Vport(
    @Json(name = "bridgeinterfaceId") val bridgeinterfaceId: String? = "",
    @Json(name = "domainId") val domainId: String? = "",
    @Json(name = "metric_info") val metricInfo: List<VportMetricInfo>? = listOf(),
    @Json(name = "subnetId") val subnetId: String? = "",
    @Json(name = "timestamp") val timestamp: Long? = 0,
    @Json(name = "vportId") val vportId: String? = "",
    @Json(name = "vportType") val vportType: String? = "",
    @Json(name = "zoneId") val zoneId: String? = ""
)