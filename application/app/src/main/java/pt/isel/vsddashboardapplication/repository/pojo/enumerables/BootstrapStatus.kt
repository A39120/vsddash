package pt.isel.vsddashboardapplication.repository.pojo.enumerables

import com.squareup.moshi.Json

enum class BootstrapStatus {
    @field:Json(name = "ACTIVE") ACTIVE,
    @field:Json(name = "CERTIFICATE_SIGNED") CERTIFICATE_SIGNED,
    @field:Json(name = "INACTIVE") INACTIVE,
    @field:Json(name = "NOTIFICATION_APP_REQ_ACK") NOTIFICATION_APP_REQ_ACK,
    @field:Json(name = "NOTIFICATION_APP_REQ_SENT") NOTIFICATION_APP_REQ_SENT
}