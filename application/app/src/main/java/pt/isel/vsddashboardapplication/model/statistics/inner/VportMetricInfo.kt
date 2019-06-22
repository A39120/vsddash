package pt.isel.vsddashboardapplication.model.statistics.inner


import com.squareup.moshi.Json

data class VportMetricInfo(
    @Json(name = "anti_spoof_drop_pkt_count") val antiSpoofDropPktCount: Int? = 0,
    @Json(name = "bytes_in") val bytesIn: Int? = 0,
    @Json(name = "bytes_out") val bytesOut: Int? = 0,
    @Json(name = "packets_dropped_rate_limit") val packetsDroppedRateLimit: Int? = 0,
    @Json(name = "packets_in") val packetsIn: Int? = 0,
    @Json(name = "packets_in_dropped") val packetsInDropped: Int? = 0,
    @Json(name = "packets_in_errors") val packetsInErrors: Int? = 0,
    @Json(name = "packets_out") val packetsOut: Int? = 0,
    @Json(name = "packets_out_dropped") val packetsOutDropped: Int? = 0,
    @Json(name = "packets_out_errors") val packetsOutErrors: Int? = 0
)