package pt.isel.vsddashboardapplication.model
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass


@JsonClass(generateAdapter = true)
@Entity(tableName = "enterprise")
data class Enterprise(
    @PrimaryKey @Json(name = "ID") val iD: String = "",
    var userId: String? = "",
    @Json(name = "allowAdvancedQOSConfiguration") val allowAdvancedQOSConfiguration: Boolean? = false,
    @Json(name = "allowGatewayManagement") val allowGatewayManagement: Boolean? = false,
    @Json(name = "allowTrustedForwardingClass") val allowTrustedForwardingClass: Boolean? = false,
    //@Json(name = "allowedForwardingClasses") val allowedForwardingClasses: List<String?>? = listOf(),
    @Json(name = "allowedForwardingMode") val allowedForwardingMode: String? = "",
    @Json(name = "associatedEnterpriseSecurityID") val associatedEnterpriseSecurityID: String? = "",
    @Json(name = "associatedGroupKeyEncryptionProfileID") val associatedGroupKeyEncryptionProfileID: String? = "",
    @Json(name = "associatedKeyServerMonitorID") val associatedKeyServerMonitorID: String? = "",
    @Json(name = "avatarData") val avatarData: String? = "",
    @Json(name = "avatarType") val avatarType: String? = "",
    @Json(name = "BGPEnabled") val bGPEnabled: Boolean? = false,
    @Json(name = "children") val children: String? = "",
    @Json(name = "creationDate") val creationDate: Long? = 0,
    @Json(name = "customerID") val customerID: Int? = 0,
    @Json(name = "DHCPLeaseInterval") val dHCPLeaseInterval: Int? = 0,
    @Json(name = "description") val description: String? = "",
    @Json(name = "dictionaryVersion") val dictionaryVersion: Int? = 0,
    @Json(name = "enableApplicationPerformanceManagement") val enableApplicationPerformanceManagement: Boolean? = false,
    @Json(name = "encryptionManagementMode") val encryptionManagementMode: String? = "",
    @Json(name = "enterpriseProfileID") val enterpriseProfileID: String? = "",
    @Json(name = "entityScope") val entityScope: String? = "",
    @Json(name = "externalID") val externalID: String? = "",
    @Json(name = "floatingIPsQuota") val floatingIPsQuota: Int? = 0,
    @Json(name = "floatingIPsUsed") val floatingIPsUsed: Int? = 0,
    @Json(name = "flowCollectionEnabled") val flowCollectionEnabled: String? = "",
    @Json(name = "LDAPAuthorizationEnabled") val lDAPAuthorizationEnabled: Boolean? = false,
    @Json(name = "LDAPEnabled") val lDAPEnabled: Boolean? = false,
    @Json(name = "lastUpdatedBy") val lastUpdatedBy: String? = "",
    @Json(name = "lastUpdatedDate") val lastUpdatedDate: Long? = 0,
    @Json(name = "localAS") val localAS: Int? = 0,
    @Json(name = "name") val name: String? = "",
    @Json(name = "owner") val owner: String? = "",
    @Json(name = "parentID") val parentID: String? = "",
    @Json(name = "parentType") val parentType: String? = "",
    @Json(name = "receiveMultiCastListID") val receiveMultiCastListID: String? = "",
    @Json(name = "sendMultiCastListID") val sendMultiCastListID: String? = "",
    @Json(name = "sharedEnterprise") val sharedEnterprise: Boolean? = false,
    @Json(name = "VNFManagementEnabled") val vNFManagementEnabled: Boolean? = false,
    @Json(name = "virtualFirewallRulesEnabled") val virtualFirewallRulesEnabled: Boolean? = false
) : BaseEvent