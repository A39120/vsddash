package pt.isel.vsddashboardapplication.model.enumerables

import com.squareup.moshi.Json

enum class Role {
    @field:Json(name="MASTER") MASTER,
    @field:Json(name="NONE") NONE,
    @field:Json(name="SLAVE") SLAVE
}