package pt.isel.vsddashboardapplication.model.statistics

import androidx.room.Entity
import com.jjoe64.graphview.series.DataPoint
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import java.util.*


@JsonClass(generateAdapter = true)
@Entity(tableName = "dpiproberstat", primaryKeys = ["timestamp", "srcNSG", "dstNSG", "nPMGroup"])
data class DpiProbestats(
    @Json(name = "AppGroupID") val appGroupID: String? = "",
    @Json(name = "AvgDelay") val avgDelay: Double? = 0.0,
    @Json(name = "AvgJitter") val avgJitter: Double? = 0.0,
    @Json(name = "AvgPktLoss") val avgPktLoss: Double? = 0.0,
    @Json(name = "APMGroup") val aPMGroup: String? = null,
    @Json(name = "ControlSessionState") val controlSessionState: String? = "",
    @Json(name = "DUCGroupID") val dUCGroupID: String? = "",
    @Json(name = "DestinationNSG") val destinationNSG: String? = "",
    @Json(name = "DstNSG") val dstNSG: String = "",
    @Json(name = "DstUplink") val dstUplink: String? = "",
    @Json(name = "DstUplinkIndex") val dstUplinkIndex: String? = "",
    @Json(name = "DstUplinkRole") val dstUplinkRole: String? = "",
    @Json(name = "MonitorPayload") val monitorPayload: Int? = 0,
    @Json(name = "MonitorProbeInterval") val monitorProbeInterval: Double? = 0.0,
    @Json(name = "MonitorProbeNoOfPackets") val monitorProbeNoOfPackets: Double? = 0.0,
    @Json(name = "MonitorServiceClass") val monitorServiceClass: String? = "",
    @Json(name = "NPMGroup") val nPMGroup: String = "",
    @Json(name = "NPMGroupID") val nPMGroupID: String? = "",
    @Json(name = "PerfMonitor") val perfMonitor: String? = "",
    @Json(name = "SourceNSG") val sourceNSG: String? = "",
    @Json(name = "SrcNSG") val srcNSG: String = "",
    @Json(name = "SrcUplink") val srcUplink: String? = "",
    @Json(name = "SrcUplinkIndex") val srcUplinkIndex: String? = "",
    @Json(name = "SrcUplinkRole") val srcUplinkRole: String? = "",
    @Json(name = "timestamp") val timestamp: Long = 0,
    @Json(name = "UnderlayID") val underlayID: Int? = 0,
    @Json(name = "UnderlayName") val underlayName: String? = ""
) {
    fun toJitterDataPoint() =
        avgJitter?.let { DataPoint(Date(timestamp), it) }

    fun toDelayDataPoint() =
        avgDelay?.let { DataPoint(Date(timestamp), it) }

    fun toPktLossDataPoint() =
        avgPktLoss?.let { DataPoint(Date(timestamp), it) }
}