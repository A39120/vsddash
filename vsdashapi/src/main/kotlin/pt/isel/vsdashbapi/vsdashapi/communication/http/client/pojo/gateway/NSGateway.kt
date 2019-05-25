package pt.isel.vsdashbapi.vsdashapi.communication.http.client.pojo.gateway

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty
import net.nuagenetworks.bambou.annotation.RestEntity
import pt.isel.vsdashbapi.vsdashapi.communication.http.client.pojo.enumerables.NSGatewayEnumerables

@JsonIgnoreProperties(ignoreUnknown = true)
@RestEntity(restName = "nsgateway", resourceName = "nsgateways")
data class NSGateway (
        @JsonProperty("AARApplicationReleaseDate") val AARApplicationReleaseDate: String? = null,
        @JsonProperty("AARApplicationVersion") val AARApplicationVersion: String? = null,
        @JsonProperty("BIOSReleaseDate") val BIOSReleaseDate: String? = null,
        @JsonProperty("BIOSVersion") val BIOSVersion: String? = null,
        @JsonProperty("CPUType") val CPUType: String? = null,
        @JsonProperty("MACAddress") val MACAddress: String? = null,
        @JsonProperty("NATTraversalEnabled") val NATTraversalEnabled: Boolean? = null,
        @JsonProperty("NSGVersion") val NSGVersion: String? = null,
        @JsonProperty("SKU") val SKU: String? = null,
        @JsonProperty("SSHService") val SSHService: NSGatewayEnumerables.SSHService? = null,
        @JsonProperty("TCPMSSEnabled") val TCPMSSEnabled: Boolean? = null,
        @JsonProperty("TCPMaximumSegmentSize") val TCPMaximumSegmentSize: Long? = null,
        @JsonProperty("TPMStatus") val TPMStatus: NSGatewayEnumerables.TPMStatus? = null,
        @JsonProperty("TPMVersion") val TPMVersion: String? = null,
        @JsonProperty("UUID") val UUID: String? = null,
        @JsonProperty("VSDAARApplicationVersion") val VSDAARApplicationVersion: String? = null,
        @JsonProperty("ZFBMatchAttribute") val ZFBMatchAttribute: NSGatewayEnumerables.ZFBMatchAttribute? = null,
        @JsonProperty("ZFBMatchValue") val ZFBMatchValue: String? = null,
        @JsonProperty("associatedGatewaySecurityID") val associatedGatewaySecurityID: String? = null,
        @JsonProperty("associatedGatewaySecurityProfileID") val associatedGatewaySecurityProfileID: String? = null,
        @JsonProperty("associatedNSGInfoID") val associatedNSGInfoID: String? = null,
        @JsonProperty("associatedNSGUpgradeProfileID") val associatedNSGUpgradeProfileID: String? = null,
        @JsonProperty("associatedOverlayManagementProfileID") val associatedOverlayManagementProfileID: String? = null,
        @JsonProperty("autoDiscGatewayID") val autoDiscGatewayID: String? = null,
        @JsonProperty("bootstrapID") val bootstrapID: String? = null,
        @JsonProperty("bootstrapStatus") val bootstrapStatus: NSGatewayEnumerables.BootstrapStatus? = null,
        @JsonProperty("configurationReloadState") val configurationReloadState: NSGatewayEnumerables.ConfigurationReloadState? = null,
        @JsonProperty("configurationStatus") val configurationStatus: NSGatewayEnumerables.ConfigurationStatus? = null,
        @JsonProperty("controlTrafficCOSValue") val controlTrafficCOSValue: Long? = null,
        @JsonProperty("controlTrafficDSCPValue") val controlTrafficDSCPValue: Long? = null,
        @JsonProperty("datapathID") val datapathID: String? = null,
        @JsonProperty("derivedSSHServiceState") val derivedSSHServiceState: NSGatewayEnumerables.DerivedSSHServiceState? = null,
        @JsonProperty("description") val description: String? = null,
        @JsonProperty("enterpriseID") val enterpriseID: String? = null,
        @JsonProperty("entityScope") val entityScope: NSGatewayEnumerables.EntityScope? = null,
        @JsonProperty("externalID") val externalID: String? = null,
        @JsonProperty("family") val family: NSGatewayEnumerables.Family? = null,
        @JsonProperty("functions") val functions: List<NSGatewayEnumerables.Functions>? = null,
        @JsonProperty("gatewayConnected") val gatewayConnected: Boolean? = null,
        @JsonProperty("inheritedSSHServiceState") val inheritedSSHServiceState: NSGatewayEnumerables.InheritedSSHServiceState? = null,
        @JsonProperty("lastConfigurationReloadTimestamp") val lastConfigurationReloadTimestamp: Long? = null,
        @JsonProperty("lastUpdatedBy") val lastUpdatedBy: String? = null,
        @JsonProperty("libraries") val libraries: String? = null,
        @JsonProperty("locationID") val locationID: String? = null,
        @JsonProperty("name") val name: String? = null,
        @JsonProperty("networkAcceleration") val networkAcceleration: NSGatewayEnumerables.NetworkAcceleration? = null,
        @JsonProperty("operationMode") val operationMode: String? = null,
        @JsonProperty("operationStatus") val operationStatus: String? = null,
        @JsonProperty("pending") val pending: Boolean? = null,
        @JsonProperty("permittedAction") val permittedAction: NSGatewayEnumerables.PermittedAction? = null,
        @JsonProperty("personality") val personality: NSGatewayEnumerables.NSGPersonality? = null,
        @JsonProperty("productName") val productName: String? = null,
        @JsonProperty("redundancyGroupID") val redundancyGroupID: String? = null,
        @JsonProperty("serialNumber") val serialNumber: String? = null,
        @JsonProperty("systemID") val systemID: String? = null,
        @JsonProperty("templateID") val templateID: String? = null
)

