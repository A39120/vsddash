package pt.isel.vsddashboardapplication.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import pt.isel.vsddashboardapplication.model.enumerables.EntityScope
import pt.isel.vsddashboardapplication.model.enumerables.Severity

@JsonClass(generateAdapter = true)
@Entity(tableName = "alarm")
data class Alarm(
    @PrimaryKey @Json(name = "ID") val iD: String = "",
    @Json(name = "acknowledged") val acknowledged: Boolean? = false,
    @Json(name = "alarmedObjectID") val alarmedObjectID: String? = "",
    @Json(name = "creationDate") val creationDate: Long? = 0,
    @Json(name = "description") val description: String? = "",
    @Json(name = "enterpriseID") val enterpriseID: String? = "",
    @Json(name = "entityScope") val entityScope: EntityScope? = null,
    @Json(name = "errorCondition") val errorCondition: Int? = 0,
    @Json(name = "lastUpdatedBy") val lastUpdatedBy: String? = "",
    @Json(name = "lastUpdatedDate") val lastUpdatedDate: Long? = 0,
    @Json(name = "name") val name: String? = "",
    @Json(name = "numberOfOccurances") val numberOfOccurances: Int? = 0,
    @Json(name = "owner") val owner: String? = "",
    @Json(name = "parentID") val parentID: String? = "",
    @Json(name = "parentType") val parentType: String? = "",
    @Json(name = "reason") val reason: String? = "",
    @Json(name = "severity") val severity: Severity? = null,
    @Json(name = "targetObject") val targetObject: String? = "",
    @Json(name = "timestamp") val timestamp: Long? = 0,
    var dirty: Boolean = false
) {
    companion object {
        fun fromMap(map: Map<String, Any?>) : Alarm? {
            return Alarm(
                map["ID"] as String,
                map["acknowledged"] as Boolean? ,
                map["alarmedObjectID"] as String? ,
                (map["creationDate"] as Double?)?.toLong(),
                map["description"] as String? ,
                map["enterpriseID"] as String? ,
                map["entityScope"] as EntityScope? ,
                map["errorCondition"] as Int? ,
                map["lastUpdatedBy"] as String? ,
                (map["lastUpdatedDate"] as Double?)?.toLong() ,
                map["name"] as String? ,
                map["numberOfOccurances"] as Int? ,
                map["owner"] as String? ,
                map["parentID"] as String? ,
                map["parentType"] as String? ,
                map["reason"] as String? ,
                map["severity"] as Severity? ,
                map["targetObject"] as String? ,
                (map["timestamp"] as Double?)?.toLong()
            )
        }
    }
}