package pt.isel.vsddashboardapplication.model
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
@Entity(tableName = "vsc")
data class VSC(
    @PrimaryKey @Json(name = "ID") val iD: String = "",
    @Json(name = "address") val address: String? = "",
    @Json(name = "addresses") val addresses: String? = "",
    @Json(name = "alreadyMarkedForUnavailable") val alreadyMarkedForUnavailable: Boolean? = false,
    @Json(name = "averageCPUUsage") val averageCPUUsage: Double? = 0.0,
    @Json(name = "averageMemoryUsage") val averageMemoryUsage: Double? = 0.0,
    @Json(name = "BGPPeers") val bGPPeers: String? = "",
    @Json(name = "children") val children: String? = "",
    @Json(name = "creationDate") val creationDate: Long? = 0,
    @Json(name = "currentCPUUsage") val currentCPUUsage: Double? = 0.0,
    @Json(name = "currentMemoryUsage") val currentMemoryUsage: Double? = 0.0,
    @Json(name = "description") val description: String? = "",
    @Json(name = "entityScope") val entityScope: String? = "",
    @Json(name = "externalID") val externalID: String? = "",
    @Json(name = "lastStateChange") val lastStateChange: Long? = 0,
    @Json(name = "lastUpdatedBy") val lastUpdatedBy: String? = "",
    @Json(name = "lastUpdatedDate") val lastUpdatedDate: Long? = 0,
    @Json(name = "location") val location: String? = "",
    @Json(name = "managementIP") val managementIP: String? = "",
    @Json(name = "messages") val messages: String? = "",
    @Json(name = "name") val name: String? = "",
    @Json(name = "owner") val owner: String? = "",
    @Json(name = "parentID") val parentID: String? = "",
    @Json(name = "parentType") val parentType: String? = "",
    @Json(name = "peakCPUUsage") val peakCPUUsage: Double? = 0.0,
    @Json(name = "peakMemoryUsage") val peakMemoryUsage: Double? = 0.0,
    @Json(name = "productVersion") val productVersion: String? = "",
    @Json(name = "status") val status: String? = "",
    @Json(name = "unavailableTimestamp") val unavailableTimestamp: Int? = 0
    //@Json(name = "vsds") val vsds: List<String?>? = listOf()
)
