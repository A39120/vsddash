package pt.isel.vsddashboardapplication.model.enumerables

import com.squareup.moshi.Json

enum class Status {
    @field:Json(name ="ADMIN_DOWN") ADMIN_DOWN { override fun toString(): String = "Admin Down" },
    @field:Json(name ="DOWN") DOWN { override fun toString(): String = "Down" },
    @field:Json(name ="UP") UP { override fun toString(): String = "Up" }
}