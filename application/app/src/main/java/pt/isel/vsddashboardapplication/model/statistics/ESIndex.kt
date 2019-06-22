package pt.isel.vsddashboardapplication.model.statistics

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

/**
 * The catalog index of elastic search
 */
@JsonClass(generateAdapter = true)
@Entity(tableName = "esindex")
data class ESIndex(
    @Json(name="health") val health: String? = null,
    @Json(name="index") val index: String? = null,
    @Json(name="status") val status: String? = null,
    @PrimaryKey @Json(name="uuid") val uuid: String? = null
)

