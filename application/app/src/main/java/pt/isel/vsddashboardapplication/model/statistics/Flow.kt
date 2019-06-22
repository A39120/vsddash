package pt.isel.vsddashboardapplication.model.statistics

import com.squareup.moshi.Json
import pt.isel.vsddashboardapplication.model.statistics.inner.NuageMetadata
import pt.isel.vsddashboardapplication.model.statistics.inner.Tcpflags

data class Flow (
    @Json(name = "bytes") val bytes: Int? = 0,
    @Json(name = "destinationip") val destinationip: String? = "",
    @Json(name = "destinationmac") val destinationmac: String? = "",
    @Json(name = "destinationport") val destinationport: String? = "",
    @Json(name = "nuage_metadata") val nuageMetadata: NuageMetadata? = NuageMetadata(),
    @Json(name = "packets") val packets: Int? = 0,
    @Json(name = "protocol") val protocol: String? = "",
    @Json(name = "sourceip") val sourceip: String? = "",
    @Json(name = "sourcemac") val sourcemac: String? = "",
    @Json(name = "sourceport") val sourceport: String? = "",
    @Json(name = "tcpflags") val tcpflags: Tcpflags? = Tcpflags(),
    @Json(name = "tcpstate") val tcpstate: Int? = 0,
    @Json(name = "timestamp") val timestamp: Long? = 0,
    @Json(name = "type") val type: String? = ""
)