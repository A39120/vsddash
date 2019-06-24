package pt.isel.vsddashboardapplication.model.statistics

import com.squareup.moshi.Json
import pt.isel.vsddashboardapplication.model.statistics.inner.AddressMapMetricInfo

/**
 * Contains information on a NSPort of received packets (byte count, packet count)
 */
data class AddressMap(
    @Json(name = "metric_info") val metricInfo: List<AddressMapMetricInfo>? = listOf(),
    @Json(name = "nsportId") val nsportId: String? = "",
    @Json(name = "timestamp") val timestamp: Long? = 0,
    @Json(name = "vlanId") val vlanId: String? = ""
)