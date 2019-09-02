package pt.isel.vsddashboardapplication.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import pt.isel.vsddashboardapplication.model.converters.BootstrapStatusConverter
import pt.isel.vsddashboardapplication.model.converters.FamilyConverter
import pt.isel.vsddashboardapplication.model.converters.PermittedActionConverter
import pt.isel.vsddashboardapplication.model.converters.PersonalityConverter
import pt.isel.vsddashboardapplication.model.enumerables.BootstrapStatus
import pt.isel.vsddashboardapplication.model.enumerables.Family
import pt.isel.vsddashboardapplication.model.enumerables.PermittedAction
import pt.isel.vsddashboardapplication.model.enumerables.Personality

@JsonClass(generateAdapter = true)
@Entity(tableName = "nsgateway")
data class NSGateway (
    @PrimaryKey @Json(name = "ID") val ID : String = "",
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
    @Json(name = "permittedAction") val permittedAction: PermittedAction? = null,
    @Json(name = "family") val family: Family? = null,
    @Json(name = "personality") val personality: Personality? = null,
    var dirty: Boolean = false

    //@JsonProperty("derivedSSHServiceState") val derivedSSHServiceState: NSGatewayEnumerables.DerivedSSHServiceState? = null,
    //@JsonProperty("inheritedSSHServiceState") val inheritedSSHServiceState: NSGatewayEnumerables.InheritedSSHServiceState? = null,
    //@JsonProperty("functions") val functions: List<NSGatewayEnumerables.Functions>? = null,
    //@JsonProperty("networkAcceleration") val networkAcceleration: NSGatewayEnumerables.NetworkAcceleration? = null,
    //@JsonProperty("configurationReloadState") val configurationReloadState: NSGatewayEnumerables.ConfigurationReloadState? = null,
    //@JsonProperty("configurationStatus") val configurationStatus: NSGatewayEnumerables.ConfigurationStatus? = null,

) {
    companion object{
        fun fromMap(map : Map<String, Any?>) : NSGateway?{
            val status = BootstrapStatusConverter().convertFrom(map["bootstrapStatus"] as String?)
            val family = FamilyConverter().convertFrom(map["family"] as String?)
            val personality = PersonalityConverter().convertFrom(map["personality"] as String?)
            val permittedAction =  PermittedActionConverter().convertFrom(map["permittedAction"] as String?)

            return NSGateway(
                ID = map["ID"] as String,
                AARApplicationVersion = map["AARApplicationVersion"] as String?,
                family = family,
                BIOSReleaseDate = map["BIOSReleaseDate"] as String?,
                BIOSVersion = map["BIOSVersion"] as String?,
                CPUType = map["CPUType"] as String?,
                MACAddress = map["MACAddress"] as String?,
                NATTraversalEnabled = map["NATTraversalEnabled"] as Boolean?,
                NSGVersion = map["NSGVersion"] as String?,
                personality = personality,
                SKU = map["SKU"] as String?,
                TCPMSSEnabled = map["TCPMSSEnabled"] as Boolean?,
                TCPMaximumSegmentSize = (map["TCPMaximumSegmentSize"] as Double?)?.toLong(),
                TPMVersion = map["TPMVersion"] as String?,
                UUID = map["UUID"] as String?,
                VSDAARApplicationVersion = map["VSDAARApplicationVersion"] as String?,
                ZFBMatchValue = map["ZFBMatchValue"] as String?,
                associatedGatewaySecurityID = map["associatedGatewaySecurityID"] as String?,
                associatedGatewaySecurityProfileID = map["associatedGatewaySecurityProfileID"] as String?,
                associatedNSGInfoID = map["associatedNSGInfoID"] as String?,
                associatedNSGUpgradeProfileID = map["associatedNSGUpgradeProfileID"] as String?,
                associatedOverlayManagementProfileID = map["associatedOverlayManagementProfileID"] as String?,
                autoDiscGatewayID = map["autoDiscGatewayID"] as String?,
                bootstrapID = map["bootstrapID"] as String?,
                controlTrafficCOSValue = (map["controlTrafficCOSValue"] as Double?)?.toLong(),
                controlTrafficDSCPValue = (map["controlTrafficDSCPValue"] as Double?)?.toLong(),
                datapathID = map["datapathID"] as String?,
                description = map["description"] as String?,
                enterpriseID = map["enterpriseID"] as String?,
                externalID = map["externalID"] as String?,
                gatewayConnected = map["gatewayConnected"] as Boolean?,
                lastConfigurationReloadTimestamp = (map["lastConfigurationReloadTimestamp"] as Double?)?.toLong(),
                lastUpdatedBy = map["lastUpdatedBy"] as String?,
                libraries = map["libraries"] as String?,
                locationID = map["locationID"] as String?,
                name = map["name"] as String?,
                operationMode = map["operationMode"] as String?,
                operationStatus = map["operationStatus"] as String?,
                pending = map["pending"] as Boolean?,
                productName = map["productName"] as String?,
                redundancyGroupID = map["redundancyGroupID"] as String?,
                serialNumber = map["serialNumber"] as String?,
                systemID = map["systemID"] as String?,
                templateID = map["templateID"] as String?,
                bootstrapStatus = status,
                permittedAction = permittedAction
            )

        }
    }
}
