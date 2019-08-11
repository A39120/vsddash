package pt.isel.vsddashboardapplication.model


import androidx.room.Entity
import androidx.room.PrimaryKey
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
@Entity(tableName = "vport")
data class VPort(
    @PrimaryKey @Json(name = "ID") val iD: String,
    @Json(name = "accessRestrictionEnabled") val accessRestrictionEnabled: Boolean? = false,
    @Json(name = "active") val active: Boolean? = false,
    @Json(name = "addressSpoofing") val addressSpoofing: String? = "",
    @Json(name = "assocEntityID") val assocEntityID: String? = "",
    @Json(name = "associatedEgressProfileID") val associatedEgressProfileID: String? = "",
    @Json(name = "associatedFloatingIPID") val associatedFloatingIPID: String? = "",
    @Json(name = "associatedGatewayID") val associatedGatewayID: String? = "",
    @Json(name = "associatedGatewayPersonality") val associatedGatewayPersonality: String? = "",
    @Json(name = "associatedGatewayType") val associatedGatewayType: String? = "",
    @Json(name = "associatedIngressProfileID") val associatedIngressProfileID: String? = "",
    @Json(name = "associatedMulticastChannelMapID") val associatedMulticastChannelMapID: String? = "",
    @Json(name = "associatedSSID") val associatedSSID: String? = "",
    @Json(name = "associatedSendMulticastChannelMapID") val associatedSendMulticastChannelMapID: String? = "",
    @Json(name = "associatedTrunkID") val associatedTrunkID: String? = "",
    @Json(name = "backhaulSubnetVNID") val backhaulSubnetVNID: String? = "",
    @Json(name = "bgpNeighbors") val bgpNeighbors: String? = "",
    @Json(name = "creationDate") val creationDate: Long? = 0,
    @Json(name = "DPI") val dPI: String? = "",
    @Json(name = "description") val description: String? = "",
    @Json(name = "domainID") val domainID: String? = "",
    @Json(name = "domainServiceLabel") val domainServiceLabel: String? = "",
    @Json(name = "domainVLANID") val domainVLANID: String? = "",
    @Json(name = "entityScope") val entityScope: String? = "",
    @Json(name = "externalID") val externalID: String? = "",
    @Json(name = "FIPIgnoreDefaultRoute") val fIPIgnoreDefaultRoute: String? = "",
    @Json(name = "gatewayMACMoveRole") val gatewayMACMoveRole: String? = "",
    @Json(name = "gatewayPortName") val gatewayPortName: String? = "",
    @Json(name = "gwEligible") val gwEligible: Boolean? = false,
    @Json(name = "hasAttachedInterfaces") val hasAttachedInterfaces: Boolean? = false,
    @Json(name = "lastUpdatedBy") val lastUpdatedBy: String? = "",
    @Json(name = "lastUpdatedDate") val lastUpdatedDate: Long? = 0,
    @Json(name = "multiNICVPortID") val multiNICVPortID: String? = "",
    @Json(name = "multicast") val multicast: String? = "",
    @Json(name = "name") val name: String? = "",
    @Json(name = "operationalState") val operationalState: String? = "",
    @Json(name = "owner") val owner: String? = "",
    @Json(name = "parentID") val parentID: String? = "",
    @Json(name = "parentType") val parentType: String? = "",
    @Json(name = "peerOperationalState") val peerOperationalState: String? = "",
    @Json(name = "segmentationID") val segmentationID: String? = "",
    @Json(name = "segmentationType") val segmentationType: String? = "",
    @Json(name = "serviceID") val serviceID: String? = "",
    @Json(name = "subType") val subType: String? = "",
    @Json(name = "subnetVNID") val subnetVNID: String? = "",
    @Json(name = "systemType") val systemType: String? = "",
    @Json(name = "trunkRole") val trunkRole: String? = "",
    @Json(name = "type") val type: String? = "",
    @Json(name = "VLAN") val vLAN: Int? = 0,
    @Json(name = "VLANID") val vLANID: String? = "",
    @Json(name = "vips") val vips: String? = "",
    @Json(name = "zoneID") val zoneID: String? = ""
)