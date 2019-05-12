package pt.isel.vsdashbapi.vsdashapi.communication.http.client.pojo.gateway


import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty
import net.nuagenetworks.bambou.annotation.RestEntity
import pt.isel.vsdashbapi.vsdashapi.communication.http.client.pojo.hardware.DiskStat
import pt.isel.vsdashbapi.vsdashapi.communication.http.client.pojo.enumerables.VRSEnumerables

@JsonIgnoreProperties(ignoreUnknown = true)
@RestEntity(restName = "vrs", resourceName = "vrss")
data class VRS(
        @JsonProperty("JSONRPCConnectionState") val JSONRPCConnectionState: VRSEnumerables.JSONRPCConnectionState? = null,
        @JsonProperty("address") val address: String? = null,
        @JsonProperty("averageCPUUsage") val averageCPUUsage: Float? = null,
        @JsonProperty("averageMemoryUsage") val averageMemoryUsage: Float? = null,
        @JsonProperty("clusterNodeRole") val clusterNodeRole: VRSEnumerables.ClusterNodeRole? = null,
        @JsonProperty("currentCPUUsage") val currentCPUUsage: Float? = null,
        @JsonProperty("currentMemoryUsage") val currentMemoryUsage: Float? = null,
        @JsonProperty("dbSynced") val dbSynced: Boolean? = null,
        @JsonProperty("description") val description: String? = null,
        @JsonProperty("disks") val disks: List<DiskStat>? = null,
        @JsonProperty("dynamic") val dynamic: Boolean? = null,
        @JsonProperty("entityScope") val entityScope: VRSEnumerables.EntityScope? = null,
        @JsonProperty("externalID") val externalID: String? = null,
        @JsonProperty("gatewayUUID") val gatewayUUID: String? = null,
        @JsonProperty("hypervisorConnectionState") val hypervisorConnectionState: VRSEnumerables.HypervisorConnectionState? = null,
        @JsonProperty("hypervisorIdentifier") val hypervisorIdentifier: String? = null,
        @JsonProperty("hypervisorName") val hypervisorName: String? = null,
        @JsonProperty("hypervisorType") val hypervisorType: String? = null,
        @JsonProperty("isResilient") val isResilient: Boolean? = null,
        @JsonProperty("lastEventName") val lastEventName: String? = null,
        @JsonProperty("lastEventObject") val lastEventObject: String? = null,
        @JsonProperty("lastEventTimestamp") val lastEventTimestamp: Long? = null,
        @JsonProperty("lastStateChange") val lastStateChange: Long? = null,
        @JsonProperty("lastUpdatedBy") val lastUpdatedBy: String? = null,
        @JsonProperty("licensedState") val licensedState: VRSEnumerables.LicensedState? = null,
        @JsonProperty("location") val location: String? = null,
        @JsonProperty("managementIP") val managementIP: String? = null,
        @JsonProperty("messages") val messages: List<String>? = null,
        @JsonProperty("multiNICVPortEnabled") val multiNICVPortEnabled: Boolean? = null,
        @JsonProperty("name") val name: String? = null,
        @JsonProperty("numberOfBridgeInterfaces") val numberOfBridgeInterfaces: Long? = null,
        @JsonProperty("numberOfContainers") val numberOfContainers: Long? = null,
        @JsonProperty("numberOfHostInterfaces") val numberOfHostInterfaces: Long? = null,
        @JsonProperty("numberOfVirtualMachines") val numberOfVirtualMachines: Long? = null,
        @JsonProperty("parentIDs") val parentIDs: List<String>? = null,
        @JsonProperty("peakCPUUsage") val peakCPUUsage: Float? = null,
        @JsonProperty("peakMemoryUsage") val peakMemoryUsage: Float? = null,
        @JsonProperty("peer") val peer: String? = null,
        @JsonProperty("personality") val personality: VRSEnumerables.Personality? = null,
        @JsonProperty("primaryVSCConnectionLost") val primaryVSCConnectionLost: Boolean? = null,
        @JsonProperty("productVersion") val productVersion: String? = null,
        @JsonProperty("revertBehaviorEnabled") val revertBehaviorEnabled: Boolean? = null,
        @JsonProperty("revertCompleted") val revertCompleted: Boolean? = null,
        @JsonProperty("revertCount") val revertCount: Long? = null,
        @JsonProperty("revertFailedCount") val revertFailedCount: Long? = null,
        @JsonProperty("role") val role: VRSEnumerables.Role? = null,
        @JsonProperty("status") val status: VRSEnumerables.Status? = null,
        @JsonProperty("uptime") val uptime: Long? = null,
        @JsonProperty("vscConfigState") val vscConfigState: VRSEnumerables.VscConfigState? = null,
        @JsonProperty("vscCurrentState") val vscCurrentState: VRSEnumerables.VscCurrentState? = null
)


