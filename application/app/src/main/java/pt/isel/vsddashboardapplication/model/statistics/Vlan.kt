package pt.isel.vsddashboardapplication.model.statistics

import com.squareup.moshi.Json
import pt.isel.vsddashboardapplication.model.statistics.inner.VlanMetricInfo

data class Vlan(
    @Json(name = "metric_info") val metricInfo: List<VlanMetricInfo>? = listOf(),
    @Json(name = "nsportId") val nsportId: String? = "",
    @Json(name = "timestamp") val timestamp: Long? = 0,
    @Json(name = "vlanId") val vlanId: String? = ""
)