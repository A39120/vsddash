package pt.isel.vsddashboardapplication.repository.pojo.statistics

import com.squareup.moshi.Json
import pt.isel.vsddashboardapplication.repository.pojo.statistics.inner.AddressMapMetricInfo

data class AddressMap(
    @Json(name = "metric_info") val metricInfo: List<AddressMapMetricInfo>? = listOf(),
    @Json(name = "nsportId") val nsportId: String? = "",
    @Json(name = "timestamp") val timestamp: Long? = 0,
    @Json(name = "vlanId") val vlanId: String? = ""
)