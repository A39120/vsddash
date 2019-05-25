package pt.isel.vsddashboardapplication.repository.pojo

import com.squareup.moshi.Json


data class Gateway(
    @Json(name = "BIOSReleaseDate") val biosReleaseDate: String?,
    @Json(name = "BIOSVersion") val biosVersion: String?,
    @Json(name = "CPUType") val cpuType: String?,
    @Json(name = "MACAddress") val macAddress: String?,
    @Json(name = "UUID") val uuid: String?,
    @Json(name = "associatedNSGInfoID") val associatedNSGInfoID: String?,
    @Json(name = "associatedNetconfProfileID") val associatedNetconfProfileID: String?,
    @Json(name = "bootstrapID") val bootstrapID: String,
    @Json(name = "description") val description: String,
    @Json(name = "enterpriseID") val enterpriseID: String,
    @Json(name = "externalID") val externalID: String,
    @Json(name = "gatewayConnected") val gatewayConnected: Boolean? = null,
    @Json(name = "gatewayModel") val gatewayModel: String,
    @Json(name = "gatewayVersion") val gatewayVersion: String,
    @Json(name = "managementID") val managementID: String,
    @Json(name = "name") val name: String,
    @Json(name = "peer") val peer: String,
    @Json(name = "pending") val pending: Boolean? = null,
    @Json(name = "productName") val productName: String,
    @Json(name = "serialNumber") val serialNumber: String,
    @Json(name = "systemID") val systemID: String,
    @Json(name = "templateID") val templateID: String
    //@JsonProperty("family") val family: GatewayEnumerables.Family,
    //@JsonProperty("bootstrapStatus") val bootstrapStatus: GatewayEnumerables.BootstrapStatus,
    //@JsonProperty("personality") val personality: GatewayEnumerables.Personality,
    //@JsonProperty("vendor") val vendor: Vendor
)
