package pt.isel.vsddashboardapplication.model.statistics

import androidx.room.Entity
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
@Entity(tableName = "sysmon", primaryKeys = ["timestamp", "systemId"])
data class Sysmon(
    @Json(name = "cpu") val cpu: Double? = 0.0,
    @Json(name = "memory") val memory: Double? = 0.0,
    @Json(name = "system_id") val systemId: String = "",
    @Json(name = "timestamp") val timestamp: Long = 0
)