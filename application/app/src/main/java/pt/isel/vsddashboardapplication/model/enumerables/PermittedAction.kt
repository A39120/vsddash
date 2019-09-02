package pt.isel.vsddashboardapplication.model.enumerables

import com.squareup.moshi.Json

enum class PermittedAction {
    @field:Json(name = "ALL") ALL,
    @field:Json(name = "DEPLOY") DEPLOY,
    @field:Json(name = "EXTEND") EXTEND,
    @field:Json(name = "INSTANTIATE") INSTANTIATE,
    @field:Json(name = "READ") READ,
    @field:Json(name = "USE") USE
}