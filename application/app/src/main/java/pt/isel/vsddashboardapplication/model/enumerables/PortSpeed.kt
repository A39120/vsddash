package pt.isel.vsddashboardapplication.model.enumerables

import com.squareup.moshi.Json

enum class PortSpeed {
    @field:Json(name = "AUTONEGOTIATE") AUTONEGOTIATE,
    @field:Json(name = "BASET10") BASET10,
    @field:Json(name = "BASET1000") BASET1000,
    @field:Json(name = "BASETX100") BASETX100,
    @field:Json(name = "BASEX10G") BASEX10G
}
