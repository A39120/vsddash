package pt.isel.vsddashboardapplication.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.squareup.moshi.Json
import pt.isel.vsddashboardapplication.model.enumerables.EntityScope

@Entity(tableName = "health")
data class Health(
    @PrimaryKey @Json(name = "ID") val iD: String = "",
    @Json(name = "entityScope") val entityScope: EntityScope? = null,
    @Json(name = "externalID") val externalID: String? = "",
    @Json(name = "parentID") val parentID: String? = "",
    @Json(name = "status") val status: String? = "",
    @Json(name = "summary") val summary: String? = ""
) {
    fun isGood() = status == "GOOD"
}