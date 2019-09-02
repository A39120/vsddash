package pt.isel.vsddashboardapplication.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import pt.isel.vsddashboardapplication.model.enumerables.EntityScope
import pt.isel.vsddashboardapplication.model.enumerables.Personality
import pt.isel.vsddashboardapplication.model.enumerables.Role
import pt.isel.vsddashboardapplication.model.enumerables.Status

@JsonClass(generateAdapter = true)
@Entity(tableName = "vrs")
data class VRS(
    @PrimaryKey @Json(name = "ID") val iD: String = "",
    @Json(name = "dynamic") val dynamic: Boolean? = false,
    @Json(name = "address") val address: String? = "",
    @Json(name = "averageCPUUsage") val averageCPUUsage: Double? = 0.0,
    @Json(name = "averageMemoryUsage") val averageMemoryUsage: Double? = 0.0,
    @Json(name = "creationDate") val creationDate: Long? = 0,
    @Json(name = "currentCPUUsage") val currentCPUUsage: Double? = 0.0,
    @Json(name = "currentMemoryUsage") val currentMemoryUsage: Double? = 0.0,
    @Json(name = "dbSynced") val dbSynced: Boolean? = false,
    @Json(name = "description") val description: String? = "",
    @Json(name = "entityScope") val entityScope: EntityScope? = null,
    @Json(name = "externalID") val externalID: String? = "",
    @Json(name = "gatewayUUID") val gatewayUUID: String? = "",
    @Json(name = "hypervisorConnectionState") val hypervisorConnectionState: Status? = null,
    @Json(name = "hypervisorIdentifier") val hypervisorIdentifier: String? = "",
    @Json(name = "hypervisorName") val hypervisorName: String? = "",
    @Json(name = "hypervisorType") val hypervisorType: String? = "",
    @Json(name = "isResilient") val isResilient: Boolean? = false,
    @Json(name = "lastEventTimestamp") val lastEventTimestamp: Long? = 0,
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
    @Json(name = "parentType") val parentType: String? = "",
    @Json(name = "peakCPUUsage") val peakCPUUsage: Double? = 0.0,
    @Json(name = "peakMemoryUsage") val peakMemoryUsage: Double? = 0.0,
    @Json(name = "peer") val peer: String? = "",
    @Json(name = "personality") val personality: Personality? = null,
    @Json(name = "primaryVSCConnectionLost") val primaryVSCConnectionLost: Boolean? = false,
    @Json(name = "productVersion") val productVersion: String? = "",
    @Json(name = "revertBehaviorEnabled") val revertBehaviorEnabled: Boolean? = false,
    @Json(name = "revertCompleted") val revertCompleted: Boolean? = false,
    @Json(name = "revertCount") val revertCount: Int? = 0,
    @Json(name = "revertFailedCount") val revertFailedCount: Int? = 0,
    @Json(name = "role") val role: Role? = null,
    @Json(name = "status") val status: Status? = null,
    @Json(name = "uptime") val uptime: Long? = 0,
    @Json(name = "vscConfigState") val vscConfigState: String? = "",
    @Json(name = "vscCurrentState") val vscCurrentState: String? = "",
    var vsc: String? = null,
    var dirty: Boolean = false
) {
    fun isUp() = status == Status.UP
}