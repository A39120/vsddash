package pt.isel.vsddashboardapplication.repository.pojo.statistics

import com.squareup.moshi.Json
import pt.isel.vsddashboardapplication.repository.pojo.statistics.inner.Disk

data class Sysmon(
    @Json(name = "cpu") val cpu: Double? = 0.0,
    @Json(name = "disks") val disks: List<Disk>? = listOf(),
    @Json(name = "memory") val memory: Double? = 0.0,
    @Json(name = "system_id") val systemId: String? = "",
    @Json(name = "timestamp") val timestamp: Long? = 0
)