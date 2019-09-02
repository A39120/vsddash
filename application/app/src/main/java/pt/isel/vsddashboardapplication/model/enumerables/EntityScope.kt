package pt.isel.vsddashboardapplication.model.enumerables

import com.squareup.moshi.Json

enum class EntityScope {
    @field:Json(name = "ENTERPRISE") ENTERPRISE,
    @field:Json(name = "GLOBAL") GLOBAL
}