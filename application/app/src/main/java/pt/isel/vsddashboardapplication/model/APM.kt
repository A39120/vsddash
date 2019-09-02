package pt.isel.vsddashboardapplication.model


import androidx.room.Entity
import androidx.room.PrimaryKey
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
@Entity(tableName = "apm")
data class APM(
    @PrimaryKey @Json(name = "ID") val iD: String = "",
    @Json(name = "appGroupUniqueId") val appGroupUniqueId: Int? = 0,
    @Json(name = "associatedPerformanceMonitorID") val associatedPerformanceMonitorID: String? = "",
    @Json(name = "creationDate") val creationDate: Long? = 0,
    @Json(name = "description") val description: String? = "",
    @Json(name = "entityScope") val entityScope: String? = "",
    @Json(name = "externalID") val externalID: String? = "",
    @Json(name = "lastUpdatedBy") val lastUpdatedBy: String? = "",
    @Json(name = "lastUpdatedDate") val lastUpdatedDate: Long? = 0,
    @Json(name = "name") val name: String? = "",
    @Json(name = "owner") val owner: String? = "",
    @Json(name = "parentID") val parentID: String? = "",
    @Json(name = "parentType") val parentType: String? = "",
    @Json(name = "readOnly") val readOnly: Boolean? = false,
    var dirty: Boolean = false
)