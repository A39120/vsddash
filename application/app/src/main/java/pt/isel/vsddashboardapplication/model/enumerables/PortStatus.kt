package pt.isel.vsddashboardapplication.model.enumerables

import com.squareup.moshi.Json

enum class PortStatus {
    @field:Json(name = "INITIALIZED") INITIALIZED,
    @field:Json(name = "MISMATCH") ISMATCH,
    @field:Json(name = "ORPHAN") ORPHAN,
    @field:Json(name = "READY") READY
}