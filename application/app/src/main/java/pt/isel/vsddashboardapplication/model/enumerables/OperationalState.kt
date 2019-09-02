package pt.isel.vsddashboardapplication.model.enumerables

import com.squareup.moshi.Json

enum class OperationalState {
    @field:Json(name ="DOWN") DOWN,
    @field:Json(name ="INIT") INIT,
    @field:Json(name ="UP") UP
}