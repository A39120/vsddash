package pt.isel.vsddashboardapplication.model.enumerables

import com.squareup.moshi.Json

enum class Family {
    @field:Json(name = "ANY") ANY { override fun toString(): String = ""},
    @field:Json(name = "NSG_AMI") NSG_AMI { override fun toString(): String = "NSG-AMI" },
    @field:Json(name = "NSG_AZ") NSG_AZ{ override fun toString(): String = "NSG-AZ" },
    @field:Json(name = "NSG_C") NSG_C { override fun toString(): String = "NSG-C" },
    @field:Json(name = "NSG_E") NSG_E { override fun toString(): String = "NSG-E" },
    @field:Json(name = "NSG_E200") NSG_E200 { override fun toString(): String = "NSG-E200" },
    @field:Json(name = "NSG_E300") NSG_E300 { override fun toString(): String = "NSG-E300" },
    @field:Json(name = "NSG_V") NSG_V { override fun toString(): String = "NSG-V" },
    @field:Json(name = "NSG_X") NSG_X { override fun toString(): String = "NSG-X" },
    @field:Json(name = "NSG_X200") NSG_X200 { override fun toString(): String = "NSG-X200" }
}