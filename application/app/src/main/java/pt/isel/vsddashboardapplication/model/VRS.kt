package pt.isel.vsddashboardapplication.model


import androidx.room.Entity
import androidx.room.PrimaryKey
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
@Entity(tableName = "vrs")
data class VRS(
    @PrimaryKey @Json(name = "ID") val iD: String = "",
    @Json(name = "dynamic") val dynamic: Boolean? = false,
    @Json(name = "address") val address: String? = "",
    @Json(name = "averageCPUUsage") val averageCPUUsage: Int? = 0,
    @Json(name = "averageMemoryUsage") val averageMemoryUsage: Int? = 0,
    //@Json(name = "children") val children: Any? = Any(),
    //@Json(name = "clusterNodeRole") val clusterNodeRole: Any? = Any(),
    @Json(name = "creationDate") val creationDate: Long? = 0,
    @Json(name = "currentCPUUsage") val currentCPUUsage: Int? = 0,
    @Json(name = "currentMemoryUsage") val currentMemoryUsage: Int? = 0,
    @Json(name = "dbSynced") val dbSynced: Boolean? = false,
    @Json(name = "description") val description: String? = "",
    //@Json(name = "disks") val disks: Any? = Any(),
    @Json(name = "entityScope") val entityScope: String? = "",
    @Json(name = "externalID") val externalID: String? = "",
    @Json(name = "gatewayUUID") val gatewayUUID: String? = "",
    @Json(name = "hypervisorConnectionState") val hypervisorConnectionState: String? = "",
    @Json(name = "hypervisorIdentifier") val hypervisorIdentifier: String? = "",
    @Json(name = "hypervisorName") val hypervisorName: String? = "",
    @Json(name = "hypervisorType") val hypervisorType: String? = "",
    @Json(name = "isResilient") val isResilient: Boolean? = false,
    //@Json(name = "JSONRPCConnectionState") val jSONRPCConnectionState: Any? = Any(),
    //@Json(name = "lastEventName") val lastEventName: Any? = Any(),
    //@Json(name = "lastEventObject") val lastEventObject: Any? = Any(),
    @Json(name = "lastEventTimestamp") val lastEventTimestamp: Int? = 0,
    @Json(name = "lastStateChange") val lastStateChange: Long? = 0,
    @Json(name = "lastUpdatedBy") val lastUpdatedBy: String? = "",
    @Json(name = "lastUpdatedDate") val lastUpdatedDate: Long? = 0,
    @Json(name = "licensedState") val licensedState: String? = "",
    @Json(name = "location") val location: String? = "",
    @Json(name = "managementIP") val managementIP: String? = "",
    @Json(name = "messages") val messages: String? = "",
    @Json(name = "multiNICVPortEnabled") val multiNICVPortEnabled: Boolean? = false,
    @Json(name = "name") val name: String? = "",
    @Json(name = "numberOfBridgeInterfaces") val numberOfBridgeInterfaces: Int? = 0,
    @Json(name = "numberOfContainers") val numberOfContainers: Int? = 0,
    @Json(name = "numberOfHostInterfaces") val numberOfHostInterfaces: Int? = 0,
    @Json(name = "numberOfVirtualMachines") val numberOfVirtualMachines: Int? = 0,
    @Json(name = "owner") val owner: String? = "",
    @Json(name = "parentID") val parentID: String? = "",
    //@Json(name = "parentIDs") val parentIDs: List<String?>? = listOf(),
    @Json(name = "parentType") val parentType: String? = "",
    @Json(name = "peakCPUUsage") val peakCPUUsage: Int? = 0,
    @Json(name = "peakMemoryUsage") val peakMemoryUsage: Int? = 0,
    @Json(name = "peer") val peer: String? = "",
    @Json(name = "personality") val personality: String? = "",
    @Json(name = "primaryVSCConnectionLost") val primaryVSCConnectionLost: Boolean? = false,
    @Json(name = "productVersion") val productVersion: String? = "",
    @Json(name = "revertBehaviorEnabled") val revertBehaviorEnabled: Boolean? = false,
    @Json(name = "revertCompleted") val revertCompleted: Boolean? = false,
    @Json(name = "revertCount") val revertCount: Int? = 0,
    @Json(name = "revertFailedCount") val revertFailedCount: Int? = 0,
    @Json(name = "role") val role: String? = "",
    @Json(name = "status") val status: String? = "",
    @Json(name = "uptime") val uptime: Int? = 0,
    @Json(name = "vscConfigState") val vscConfigState: String? = "",
    @Json(name = "vscCurrentState") val vscCurrentState: String? = ""
)