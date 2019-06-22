package pt.isel.vsddashboardapplication.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
@Entity(tableName = "alarm")
data class Alarm(
    @PrimaryKey @Json(name = "ID") val iD: String = "",
    @Json(name = "acknowledged") val acknowledged: Boolean? = false,
    @Json(name = "alarmedObjectID") val alarmedObjectID: String? = "",
    @Json(name = "creationDate") val creationDate: Long? = 0,
    @Json(name = "description") val description: String? = "",
    @Json(name = "enterpriseID") val enterpriseID: String? = "",
    @Json(name = "entityScope") val entityScope: String? = "",
    @Json(name = "errorCondition") val errorCondition: Int? = 0,
    @Json(name = "externalID") val externalID: String? = "",
    @Json(name = "lastUpdatedBy") val lastUpdatedBy: String? = "",
    @Json(name = "lastUpdatedDate") val lastUpdatedDate: Long? = 0,
    @Json(name = "name") val name: String? = "",
    @Json(name = "numberOfOccurances") val numberOfOccurances: Int? = 0,
    @Json(name = "owner") val owner: String? = "",
    @Json(name = "parentID") val parentID: String? = "",
    @Json(name = "parentType") val parentType: String? = "",
    @Json(name = "reason") val reason: String? = "",
    @Json(name = "severity") val severity: String? = "",
    @Json(name = "targetObject") val targetObject: String? = "",
    @Json(name = "timestamp") val timestamp: Long? = 0
)