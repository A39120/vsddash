package pt.isel.vsddashboardapplication.model.enumerables

import com.squareup.moshi.Json

enum class PortType {
    @field:Json(name = "BRIDGE") BRIDGE { override fun toString(): String = "Bridge" },
    @field:Json(name = "ACCESS") ACCESS { override fun toString(): String = "Access" },
    @field:Json(name = "NETWORK") NETWORK { override fun toString(): String = "Network" }
}
