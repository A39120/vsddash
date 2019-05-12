package pt.isel.vsddashboardapplication.repository.pojo

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty


@JsonIgnoreProperties(ignoreUnknown = true)
data class Gateway(
    @JsonProperty("BIOSReleaseDate") val biosReleaseDate: String?,
    @JsonProperty("BIOSVersion") val biosVersion: String?,
    @JsonProperty("CPUType") val cpuType: String?,
    @JsonProperty("MACAddress") val macAddress: String?,
    @JsonProperty("UUID") val uuid: String?,
    @JsonProperty("associatedNSGInfoID") val associatedNSGInfoID: String?,
    @JsonProperty("associatedNetconfProfileID") val associatedNetconfProfileID: String?,
    @JsonProperty("bootstrapID") val bootstrapID: String,
    //@JsonProperty("bootstrapStatus") val bootstrapStatus: GatewayEnumerables.BootstrapStatus,
    @JsonProperty("description") val description: String,
    @JsonProperty("enterpriseID") val enterpriseID: String,
    @JsonProperty("externalID") val externalID: String,
    //@JsonProperty("family") val family: GatewayEnumerables.Family,
    @JsonProperty("gatewayConnected") val gatewayConnected: Boolean? = null,
    @JsonProperty("gatewayModel") val gatewayModel: String,
    @JsonProperty("gatewayVersion") val gatewayVersion: String,
    @JsonProperty("managementID") val managementID: String,
    @JsonProperty("name") val name: String,
    @JsonProperty("peer") val peer: String,
    @JsonProperty("pending") val pending: Boolean? = null,
    //@JsonProperty("personality") val personality: GatewayEnumerables.Personality,
    @JsonProperty("productName") val productName: String,
    @JsonProperty("serialNumber") val serialNumber: String,
    @JsonProperty("systemID") val systemID: String,
    @JsonProperty("templateID") val templateID: String
    //@JsonProperty("vendor") val vendor: Vendor
)