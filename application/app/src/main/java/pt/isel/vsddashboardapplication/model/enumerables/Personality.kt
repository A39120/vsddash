package pt.isel.vsddashboardapplication.model.enumerables

import com.squareup.moshi.Json

enum class Personality {
    @field:Json(name = "NSGBR") NSGBR { override fun toString(): String  = "NSG Border Router"},
    @field:Json(name = "NSG") NSG { override fun toString(): String  = "NSG"},
    @field:Json(name = "NSGDUC") NSGDUC { override fun toString(): String  = "NSG DUC"},
    @field:Json(name = "HARDWARE_VTEP")  HARDWARE_VTEP{ override fun toString(): String  = "VTEP"},
    @field:Json(name = "NONE")  NONE { override fun toString(): String  = "None" },
    @field:Json(name = "VRS")  VRS { override fun toString(): String  = "VRS" },
    @field:Json(name = "NUAGE_210_WBX_32_Q")  NUAGE_210_WBX_32_Q { override fun toString(): String  = "210 WBX 32Q" },
    @field:Json(name = "NUAGE_210_WBX_48_S")  NUAGE_210_WBX_48_S { override fun toString(): String  = "210 WBX 48S" },
    @field:Json(name = "VRSB")  VRSB { override fun toString(): String  = "VRSB" },
    @field:Json(name = "VRSG")  VRSG { override fun toString(): String  = "VRSG" },
}