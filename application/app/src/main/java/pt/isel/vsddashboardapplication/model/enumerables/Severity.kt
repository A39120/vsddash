package pt.isel.vsddashboardapplication.model.enumerables

import com.squareup.moshi.Json

enum class Severity {
    @field:Json(name = "CRITICAL") CRITICAL,
    @field:Json(name = "INFO") INFO,
    @field:Json(name = "MAJOR") MAJOR,
    @field:Json(name = "MINOR") MINOR,
    @field:Json(name = "WARNING") WARNING
}