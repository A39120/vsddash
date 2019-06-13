package pt.isel.vsddashboardapplication.repository.pojo.statistics

import com.squareup.moshi.Json

data class Natt(
    @Json(name = "DestinationNSG") val destinationNSG: String? = "",
    @Json(name = "DstNSG") val dstNSG: String? = "",
    @Json(name = "EnterpriseName") val enterpriseName: String? = "",
    @Json(name = "ReachabilityStatus") val reachabilityStatus: String? = "",
    @Json(name = "SourceNSG") val sourceNSG: String? = "",
    @Json(name = "SrcNSG") val srcNSG: String? = "",
    @Json(name = "timestamp") val timestamp: Long? = 0
)