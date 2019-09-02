package pt.isel.vsddashboardapplication.model.enumerables

import com.squareup.moshi.Json

enum class SystemType {
    @field:Json(name="BRIDGE") BRIDGE,
    @field:Json(name="CONTAINER") CONTAINER,
    @field:Json(name="HOST") HOST,
    @field:Json(name="VM") VM
}