package pt.isel.vsddashboardapplication.model.statistics.inner

import com.squareup.moshi.Json

data class NuageMetadata(
    @Json(name = "aclId") val aclId: String? = "",
    @Json(name = "actions") val actions: List<Action?>? = listOf(),
    @Json(name = "direction") val direction: String? = "",
    @Json(name = "domainId") val domainId: String? = "",
    @Json(name = "domainName") val domainName: String? = "",
    @Json(name = "dpgName") val dpgName: Any? = Any(),
    @Json(name = "dst-subnetmem") val dstSubnetmem: String? = "",
    @Json(name = "dst-zonemem") val dstZonemem: String? = "",
    @Json(name = "enterpriseID") val enterpriseID: String? = "",
    @Json(name = "enterpriseName") val enterpriseName: String? = "",
    @Json(name = "evpnId") val evpnId: String? = "",
    @Json(name = "flowid") val flowid: String? = "",
    @Json(name = "spgName") val spgName: Any? = Any(),
    @Json(name = "subnetId") val subnetId: String? = "",
    @Json(name = "subnetName") val subnetName: String? = "",
    @Json(name = "vportId") val vportId: String? = "",
    @Json(name = "vrfId") val vrfId: String? = "",
    @Json(name = "zoneId") val zoneId: String? = "",
    @Json(name = "zoneName") val zoneName: String? = ""
)