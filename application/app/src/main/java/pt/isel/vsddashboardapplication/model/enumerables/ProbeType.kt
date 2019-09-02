package pt.isel.vsddashboardapplication.model.enumerables

import com.squareup.moshi.Json

enum class ProbeType {
    @field:Json(name ="HTTP") HTTP,
    @field:Json(name ="IPSEC_AND_VXLAN") IPSEC_AND_VXLAN,
    @field:Json(name ="ONEWAY") ONEWAY
}