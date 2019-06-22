package pt.isel.vsddashboardapplication.model.statistics.inner


import com.squareup.moshi.Json

data class VlanMetricInfo(
    @Json(name = "rx_bytes") val rxBytes: Int? = 0,
    @Json(name = "rx_dropped") val rxDropped: Int? = 0,
    @Json(name = "rx_errors") val rxErrors: Int? = 0,
    @Json(name = "rx_pkt_count") val rxPktCount: Int? = 0,
    @Json(name = "tx_bytes") val txBytes: Int? = 0,
    @Json(name = "tx_dropped") val txDropped: Int? = 0,
    @Json(name = "tx_errors") val txErrors: Int? = 0,
    @Json(name = "tx_pkt_count") val txPktCount: Int? = 0
)