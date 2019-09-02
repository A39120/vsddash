package pt.isel.vsddashboardapplication.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import pt.isel.vsddashboardapplication.model.converters.EntityScopeConverter
import pt.isel.vsddashboardapplication.model.converters.PermittedActionConverter
import pt.isel.vsddashboardapplication.model.converters.PortStatusConverter
import pt.isel.vsddashboardapplication.model.converters.PortTypeConverter
import pt.isel.vsddashboardapplication.model.enumerables.EntityScope
import pt.isel.vsddashboardapplication.model.enumerables.PermittedAction
import pt.isel.vsddashboardapplication.model.enumerables.PortStatus
import pt.isel.vsddashboardapplication.model.enumerables.PortType

@JsonClass(generateAdapter = true)
@Entity(tableName = "nsport")
data class NSPort(
    @PrimaryKey @Json(name = "ID") val iD: String = "",
    @Json(name = "associatedRedundantPortID") val associatedRedundantPortID: String? = "",
    @Json(name = "creationDate") val creationDate: Long? = 0,
    @Json(name = "description") val description: String? = "",
    @Json(name = "enableNATProbes") val enableNATProbes: Boolean? = false,
    @Json(name = "entityScope") val entityScope: EntityScope? = null,
    @Json(name = "externalID") val externalID: String? = "",
    @Json(name = "lastUpdatedDate") val lastUpdatedDate: Long? = 0,
    @Json(name = "mtu") val mtu: Int? = 0,
    @Json(name = "NATTraversal") val nATTraversal: String? = "",
    @Json(name = "name") val name: String? = "",
    @Json(name = "owner") val owner: String? = "",
    @Json(name = "parentID") val parentID: String? = "",
    @Json(name = "parentType") val parentType: String? = "",
    @Json(name = "permittedAction") val permittedAction: PermittedAction? = null,
    @Json(name = "physicalName") val physicalName: String? = "",
    @Json(name = "portType") val portType: PortType? = null,
    @Json(name = "shuntPort") val shuntPort: Boolean? = false,
    @Json(name = "speed") val speed: String? = "",
    @Json(name = "status") val status: PortStatus? = null,
    @Json(name = "TrafficThroughUBROnly") val trafficThroughUBROnly: Boolean? = false,
    @Json(name = "useUserMnemonic") val useUserMnemonic: Boolean? = false,
    @Json(name = "userMnemonic") val userMnemonic: String? = "",
    @Json(name = "VLANRange") val vLANRange: String? = ""
) {
    companion object{
        fun fromMap(map: Map<String, Any?>) : NSPort? {
            val entityScope = EntityScopeConverter().convertFrom(map["entityScope"] as String?)
            val permittedAction = PermittedActionConverter().convertFrom(map["permittedAction"] as String?)
            val portType = PortTypeConverter().convertFrom(map["portType"] as String?)
            val status = PortStatusConverter().convertFrom(map["status"] as String?)

            return NSPort(
                iD = map["ID"] as String,
                associatedRedundantPortID = map["associatedRedundantPortID"] as String?,
                creationDate =              (map["creationDate"] as Double?)?.toLong(),
                description =               map["description"] as String?,
                enableNATProbes =           map["enableNATProbes"] as Boolean?,
                entityScope =               entityScope,
                externalID =                map["externalID"] as String?,
                lastUpdatedDate =           (map["lastUpdatedDate"] as Double?)?.toLong(),
                mtu =                       (map["mtu"] as Double?)?.toInt(),
                nATTraversal =              map["NATTraversal"] as String?,
                name =                      map["name"] as String?,
                owner =                     map["owner"] as String?,
                parentID =                  map["parentID"] as String?,
                parentType =                map["parentType"] as String?,
                permittedAction =           permittedAction,
                physicalName =              map["physicalName"] as String?,
                portType =                  portType,
                shuntPort =                 map["shuntPort"] as Boolean?,
                speed =                     map["speed"] as String?,
                status =                    status,
                trafficThroughUBROnly =     map["TrafficThroughUBROnly"] as Boolean?,
                useUserMnemonic =           map["useUserMnemonic"] as Boolean?,
                userMnemonic =              map["userMnemonic"] as String?,
                vLANRange =                 map["VLANRange"] as String?
            )
        }
    }
}