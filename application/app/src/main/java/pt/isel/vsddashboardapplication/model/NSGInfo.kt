package pt.isel.vsddashboardapplication.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import pt.isel.vsddashboardapplication.model.enumerables.*

@JsonClass(generateAdapter = true)
@Entity(tableName = "nsginfo")
data class NSGInfo(
    @PrimaryKey @Json(name = "ID") val iD: String = "",
    @Json(name = "AARApplicationReleaseDate") val aARApplicationReleaseDate: String? = "",
    @Json(name = "AARApplicationVersion") val aARApplicationVersion: String? = "",
    @Json(name = "associatedEntityType") val associatedEntityType: String? = "",
    @Json(name = "associatedNSGatewayID") val associatedNSGatewayID: String? = "",
    @Json(name = "BIOSReleaseDate") val bIOSReleaseDate: String? = "",
    @Json(name = "BIOSVersion") val bIOSVersion: String? = "",
    @Json(name = "bootstrapStatus") val bootstrapStatus: BootstrapStatus? = BootstrapStatus.INACTIVE,
    @Json(name = "CPUType") val cPUType: String? = "",
    @Json(name = "cmdDetailedStatus") val cmdDetailedStatus: String? = "",
    @Json(name = "cmdDetailedStatusCode") val cmdDetailedStatusCode: Int? = 0,
    @Json(name = "cmdDownloadProgress") val cmdDownloadProgress: String? = "",
    @Json(name = "cmdID") val cmdID: String? = "",
    @Json(name = "cmdLastUpdatedDate") val cmdLastUpdatedDate: Long? = 0,
    @Json(name = "cmdStatus") val cmdStatus: String? = "",
    @Json(name = "cmdType") val cmdType: String? = "",
    @Json(name = "creationDate") val creationDate: Long? = 0,
    @Json(name = "enterpriseID") val enterpriseID: String? = "",
    @Json(name = "enterpriseName") val enterpriseName: String? = "",
    @Json(name = "entityScope") val entityScope: EntityScope? = null,
    @Json(name = "externalID") val externalID: String? = "",
    @Json(name = "family") val family: Family? = null,
    @Json(name = "lastUpdatedBy") val lastUpdatedBy: String? = "",
    @Json(name = "lastUpdatedDate") val lastUpdatedDate: Long? = 0,
    @Json(name = "libraries") val libraries: String? = "",
    @Json(name = "MACAddress") val mACAddress: String? = "",
    @Json(name = "NSGVersion") val nSGVersion: String? = "",
    @Json(name = "name") val name: String? = "",
    @Json(name = "owner") val owner: String? = "",
    @Json(name = "parentID") val parentID: String? = "",
    @Json(name = "parentType") val parentType: String? = "",
    @Json(name = "patchesDetail") val patchesDetail: String? = "",
    @Json(name = "personality") val personality: Personality? = null,
    @Json(name = "productName") val productName: String? = "",
    @Json(name = "SKU") val sKU: String? = "",
    @Json(name = "serialNumber") val serialNumber: String? = "",
    @Json(name = "systemID") val systemID: String? = "",
    @Json(name = "TPMStatus") val tPMStatus: Int? = 0,
    @Json(name = "TPMVersion") val tPMVersion: String? = "",
    @Json(name = "UUID") val uUID: String? = ""
)