package pt.isel.vsddashboardapplication.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import pt.isel.vsddashboardapplication.model.enumerables.EntityScope

@JsonClass(generateAdapter = true)
@Entity(tableName = "vsp")
data class VSP(
    @PrimaryKey @Json(name = "ID") val iD: String = "",
    @Json(name = "children") val children: String? = "",
    @Json(name = "creationDate") val creationDate: Long? = 0,
    @Json(name = "description") val description: String? = "",
    @Json(name = "entityScope") val entityScope: EntityScope? = null,
    @Json(name = "externalID") val externalID: String? = "",
    @Json(name = "lastUpdatedBy") val lastUpdatedBy: String? = "",
    @Json(name = "lastUpdatedDate") val lastUpdatedDate: Long? = 0,
    @Json(name = "location") val location: String? = "",
    @Json(name = "name") val name: String? = "",
    @Json(name = "owner") val owner: String? = "",
    @Json(name = "parentID") val parentID: String? = "",
    @Json(name = "parentType") val parentType: String? = "",
    @Json(name = "productVersion") val productVersion: String? = ""
)