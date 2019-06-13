package pt.isel.vsddashboardapplication.repository.pojo.statistics

import com.squareup.moshi.Json

data class DpiSlaStatus(
    @Json(name = "APMGroup") val aPMGroup: String? = "",
    @Json(name = "AppGroupID") val appGroupID: String? = "",
    @Json(name = "AppID") val appID: String? = "",
    @Json(name = "Application") val application: String? = "",
    @Json(name = "DestinationNSG") val destinationNSG: String? = "",
    @Json(name = "Domain") val domain: String? = "",
    @Json(name = "DstIp") val dstIp: String? = "",
    @Json(name = "DstNSG") val dstNSG: String? = "",
    @Json(name = "DstPort") val dstPort: Int? = 0,
    @Json(name = "DstUplink") val dstUplink: String? = "",
    @Json(name = "DstUplinkIndex") val dstUplinkIndex: String? = "",
    @Json(name = "DstUplinkRole") val dstUplinkRole: String? = "",
    @Json(name = "EnterpriseName") val enterpriseName: String? = "",
    @Json(name = "EvpnID") val evpnID: Int? = 0,
    @Json(name = "L4Classification") val l4Classification: String? = "",
    @Json(name = "SourceNSG") val sourceNSG: String? = "",
    @Json(name = "SrcIp") val srcIp: String? = "",
    @Json(name = "SrcNSG") val srcNSG: String? = "",
    @Json(name = "SrcPort") val srcPort: Int? = 0,
    @Json(name = "SrcUplink") val srcUplink: String? = "",
    @Json(name = "SrcUplinkIndex") val srcUplinkIndex: String? = "",
    @Json(name = "SrcUplinkRole") val srcUplinkRole: String? = "",
    @Json(name = "timestamp") val timestamp: Long? = 0,
    @Json(name = "UnderlayID") val underlayID: Int? = 0,
    @Json(name = "ViolationType") val violationType: String? = "",
    @Json(name = "VrfID") val vrfID: Int? = 0
)