package pt.isel.vsdashbapi.vsdashapi.communication.http.client.pojo.gateway

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty
import net.nuagenetworks.bambou.annotation.RestEntity
import pt.isel.vsdashbapi.vsdashapi.communication.http.client.pojo.enumerables.EntityScope
import pt.isel.vsdashbapi.vsdashapi.communication.http.client.pojo.enumerables.GatewayEnumerables
import pt.isel.vsdashbapi.vsdashapi.communication.http.client.pojo.enumerables.Vendor

@JsonIgnoreProperties(ignoreUnknown = true)
@RestEntity(restName = "gateway", resourceName = "gateways")
data class Gateway(
        @JsonProperty("BIOSReleaseDate") val biosReleaseDate: String,
        @JsonProperty("BIOSVersion") val biosVersion: String,
        @JsonProperty("CPUType") val cpuType: String,
        @JsonProperty("MACAddress") val macAddress: String,
        @JsonProperty("UUID") val uuid: String,
        @JsonProperty("ZFBMatchAttribute") val zfbMatchAttribute: GatewayEnumerables.ZFBMatchAttribute,
        @JsonProperty("ZFBMatchValue") val zfbMatchValue: String,
        @JsonProperty("associatedGatewaySecurityID") val associatedGatewaySecurityID: String,
        @JsonProperty("associatedGatewaySecurityProfileID") val associatedGatewaySecurityProfileID: String,
        @JsonProperty("associatedNSGInfoID") val associatedNSGInfoID: String,
        @JsonProperty("associatedNetconfProfileID") val associatedNetconfProfileID: String,
        @JsonProperty("autoDiscGatewayID") val autoDiscGatewayID: String,
        @JsonProperty("bootstrapStatus") val bootstrapStatus: GatewayEnumerables.BootstrapStatus,
        @JsonProperty("description") val description: String,
        @JsonProperty("enterpriseID") val enterpriseID: String,
        @JsonProperty("entityScope") val entityScope: EntityScope,
        @JsonProperty("externalID") val externalID: String,
        @JsonProperty("family") val family: GatewayEnumerables.Family,
        @JsonProperty("gatewayConnected") val gatewayConnected: Boolean? = null,
        @JsonProperty("gatewayModel") val gatewayModel: String,
        @JsonProperty("gatewayVersion") val gatewayVersion: String,
        @JsonProperty("lastUpdatedBy") val lastUpdatedBy: String,
        @JsonProperty("libraries") val libraries: String,
        @JsonProperty("locationID") val locationID: String,
        @JsonProperty("managementID") val managementID: String,
        @JsonProperty("name") val name: String,
        @JsonProperty("patches") val patches: String,
        @JsonProperty("peer") val peer: String,
        @JsonProperty("pending") val pending: Boolean? = null,
        @JsonProperty("permittedAction") val permittedAction: GatewayEnumerables.PermittedAction,
        @JsonProperty("personality") val personality: GatewayEnumerables.Personality,
        @JsonProperty("productName") val productName: String,
        @JsonProperty("serialNumber") val serialNumber: String,
        @JsonProperty("systemID") val systemID: String,
        @JsonProperty("vendor") val vendor: Vendor,
        @JsonProperty("vtep") val vtep: String
)
