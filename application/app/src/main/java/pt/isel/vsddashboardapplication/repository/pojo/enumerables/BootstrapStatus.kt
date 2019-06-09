package pt.isel.vsddashboardapplication.repository.pojo.enumerables

import androidx.room.TypeConverters
import com.squareup.moshi.Json
import pt.isel.vsddashboardapplication.repository.pojo.converters.BootstapStatusConverter

enum class BootstrapStatus {
    @field:Json(name = "ACTIVE") ACTIVE,
    @field:Json(name = "CERTIFICATE_SIGNED") CERTIFICATE_SIGNED,
    @field:Json(name = "INACTIVE") INACTIVE,
    @field:Json(name = "NOTIFICATION_APP_REQ_ACK") NOTIFICATION_APP_REQ_ACK,
    @field:Json(name = "NOTIFICATION_APP_REQ_SENT") NOTIFICATION_APP_REQ_SENT
}