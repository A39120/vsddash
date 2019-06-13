package pt.isel.vsddashboardapplication.repository.pojo

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
@Entity(tableName = "nsport")
data class NSPort(
    @PrimaryKey @Json(name = "ID") val iD: String? = "",
    @Json(name = "associatedEgressQOSPolicyID") val associatedEgressQOSPolicyID: String? = "",
    @Json(name = "associatedRedundantPortID") val associatedRedundantPortID: String? = "",
    @Json(name = "children") val children: String? = "",
    @Json(name = "creationDate") val creationDate: Long? = 0,
    @Json(name = "description") val description: String? = "",
    @Json(name = "enableNATProbes") val enableNATProbes: Boolean? = false,
    @Json(name = "entityScope") val entityScope: String? = "",
    @Json(name = "externalID") val externalID: String? = "",
    @Json(name = "lastUpdatedBy") val lastUpdatedBy: String? = "",
    @Json(name = "lastUpdatedDate") val lastUpdatedDate: Long? = 0,
    @Json(name = "mtu") val mtu: Int? = 0,
    @Json(name = "NATTraversal") val nATTraversal: String? = "",
    @Json(name = "name") val name: String? = "",
    @Json(name = "owner") val owner: String? = "",
    @Json(name = "parentID") val parentID: String? = "",
    @Json(name = "parentType") val parentType: String? = "",
    @Json(name = "permittedAction") val permittedAction: String? = "",
    @Json(name = "physicalName") val physicalName: String? = "",
    @Json(name = "portType") val portType: String? = "",
    @Json(name = "shuntPort") val shuntPort: Boolean? = false,
    @Json(name = "speed") val speed: String? = "",
    @Json(name = "status") val status: String? = "",
    @Json(name = "templateID") val templateID: String? = "",
    @Json(name = "TrafficThroughUBROnly") val trafficThroughUBROnly: Boolean? = false,
    @Json(name = "useUserMnemonic") val useUserMnemonic: Boolean? = false,
    @Json(name = "userMnemonic") val userMnemonic: String? = "",
    @Json(name = "VLANRange") val vLANRange: String? = ""
)