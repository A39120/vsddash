package pt.isel.vsddashboardapplication.repository.pojo

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import pt.isel.vsddashboardapplication.repository.pojo.enumerables.BootstrapStatus

@JsonClass(generateAdapter = true)
@Entity(tableName = "nsgateway")
data class NSGateway (
    @Json(name = "AARApplicationReleaseDate") val AARApplicationReleaseDate: String? = null,
    @Json(name = "AARApplicationVersion") val AARApplicationVersion: String? = null,
    @Json(name = "BIOSReleaseDate") val BIOSReleaseDate: String? = null,
    @Json(name = "BIOSVersion") val BIOSVersion: String? = null,
    @Json(name = "CPUType") val CPUType: String? = null,
    @Json(name = "MACAddress") val MACAddress: String? = null,
    @Json(name = "NATTraversalEnabled") val NATTraversalEnabled: Boolean? = null,
    @Json(name = "NSGVersion") val NSGVersion: String? = null,
    @Json(name = "SKU") val SKU: String? = null,
    @Json(name = "TCPMSSEnabled") val TCPMSSEnabled: Boolean? = null,
    @Json(name = "TCPMaximumSegmentSize") val TCPMaximumSegmentSize: Long? = null,
    @Json(name = "TPMVersion") val TPMVersion: String? = null,
    @Json(name = "UUID") val UUID: String? = null,
    @Json(name = "VSDAARApplicationVersion") val VSDAARApplicationVersion: String? = null,
    @Json(name = "ZFBMatchValue") val ZFBMatchValue: String? = null,
    @Json(name = "associatedGatewaySecurityID") val associatedGatewaySecurityID: String? = null,
    @Json(name = "associatedGatewaySecurityProfileID") val associatedGatewaySecurityProfileID: String? = null,
    @Json(name = "associatedNSGInfoID") val associatedNSGInfoID: String? = null,
    @Json(name = "associatedNSGUpgradeProfileID") val associatedNSGUpgradeProfileID: String? = null,
    @Json(name = "associatedOverlayManagementProfileID") val associatedOverlayManagementProfileID: String? = null,
    @Json(name = "autoDiscGatewayID") val autoDiscGatewayID: String? = null,
    @Json(name = "bootstrapID") val bootstrapID: String? = null,
    @Json(name = "controlTrafficCOSValue") val controlTrafficCOSValue: Long? = null,
    @Json(name = "controlTrafficDSCPValue") val controlTrafficDSCPValue: Long? = null,
    @Json(name = "datapathID") val datapathID: String? = null,
    @Json(name = "description") val description: String? = null,
    @Json(name = "enterpriseID") val enterpriseID: String? = null,
    @Json(name = "externalID") val externalID: String? = null,
    @Json(name = "gatewayConnected") val gatewayConnected: Boolean? = null,
    @Json(name = "lastConfigurationReloadTimestamp") val lastConfigurationReloadTimestamp: Long? = null,
    @Json(name = "lastUpdatedBy") val lastUpdatedBy: String? = null,
    @Json(name = "libraries") val libraries: String? = null,
    @Json(name = "locationID") val locationID: String? = null,
    @Json(name = "name") val name: String? = null,
    @Json(name = "operationMode") val operationMode: String? = null,
    @Json(name = "operationStatus") val operationStatus: String? = null,
    @Json(name = "pending") val pending: Boolean? = null,
    @Json(name = "productName") val productName: String? = null,
    @Json(name = "redundancyGroupID") val redundancyGroupID: String? = null,
    @Json(name = "serialNumber") val serialNumber: String? = null,
    @Json(name = "systemID") val systemID: String? = null,
    @Json(name = "templateID") val templateID: String? = null,
    @Json(name = "bootstrapStatus") val bootstrapStatus: BootstrapStatus? = null,
    @PrimaryKey @Json(name = "ID") val ID : String = ""
    //@JsonProperty("derivedSSHServiceState") val derivedSSHServiceState: NSGatewayEnumerables.DerivedSSHServiceState? = null,
    //@JsonProperty("inheritedSSHServiceState") val inheritedSSHServiceState: NSGatewayEnumerables.InheritedSSHServiceState? = null,
    //@JsonProperty("family") val family: NSGatewayEnumerables.Family? = null,
    //@JsonProperty("functions") val functions: List<NSGatewayEnumerables.Functions>? = null,
    //@JsonProperty("networkAcceleration") val networkAcceleration: NSGatewayEnumerables.NetworkAcceleration? = null,
    //@JsonProperty("permittedAction") val permittedAction: NSGatewayEnumerables.PermittedAction? = null,
    //@JsonProperty("personality") val personality: NSGatewayEnumerables.NSGPersonality? = null,
    //@JsonProperty("bootstrapStatus") val bootstrapStatus: NSGatewayEnumerables.BootstrapStatus? = null,
    //@JsonProperty("configurationReloadState") val configurationReloadState: NSGatewayEnumerables.ConfigurationReloadState? = null,
    //@JsonProperty("configurationStatus") val configurationStatus: NSGatewayEnumerables.ConfigurationStatus? = null,
)