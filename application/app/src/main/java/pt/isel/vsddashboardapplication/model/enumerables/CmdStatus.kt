package pt.isel.vsddashboardapplication.model.enumerables

import com.squareup.moshi.Json

enum class CmdStatus {
     @field:Json(name = "ABANDONED") ABANDONED{ override fun toString(): String = "Abandoned" },
     @field:Json(name = "COMPLETED") COMPLETED{ override fun toString(): String = "Completed" },
     @field:Json(name = "FAILED") FAILED{ override fun toString(): String = "Failed" },
     @field:Json(name = "RUNNING") RUNNING{ override fun toString(): String = "Running" },
     @field:Json(name = "SKIPPED") SKIPPED{ override fun toString(): String = "Skipped" },
     @field:Json(name = "STARTED") STARTED{ override fun toString(): String = "Started" },
     @field:Json(name = "UNKNOWN") UNKNOWN{ override fun toString(): String = "Unknown" }
}

