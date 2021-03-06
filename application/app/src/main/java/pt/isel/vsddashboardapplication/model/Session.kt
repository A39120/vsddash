package pt.isel.vsddashboardapplication.model

import com.squareup.moshi.Json


data class Session(
    @field:Json(name = "ID") val ID : String? = null,
    @field:Json(name = "AARApplicationReleaseDate") val AARApplicationReleaseDate: String? = null,
    @field:Json(name = "AARProbeStatsInterval") val AARProbeStatsInterval: String? = null,
    @field:Json(name = "elasticSearchAddress") val elasticSearchAddress: String? = null,
    @field:Json(name = "enterpriseID") val enterpriseID : String? = null,
    @field:Json(name = "enterpriseName") val enterpriseName : String? = null,
    @field:Json(name = "firstName") val firstName : String? = null,
    @field:Json(name = "flowCollectionEnabled") val flowCollectionEnabled : Boolean? = false,
    @field:Json(name = "role") val role : String? = null,
    @field:Json(name = "statisticsEnabled") val statisticsEnabled : Boolean? = false,
    @field:Json(name = "statsTSDBServerAddress") val statsTSDBServerAddress: String? = null,
    @field:Json(name = "APIKey") val APIKey : String? = null,
    @field:Json(name = "APIKeyExpiry") val APIKeyExpiry : Long? = 0
)

/*
@JsonProperty("enterpriseID") val enterpriseId : String? = null,
@JsonProperty("enterpriseName") val enterpriseName : String? = null,
@JsonProperty("firstName") val name : String? = null,
@JsonProperty("flowCollectionEnabled") val flowCollectionEnabled : Boolean? = false,
@JsonProperty("role") val role : String? = null,
@JsonProperty("statisticsEnabled") val statsEnabled : Boolean? = false

@JsonProperty("entityScope") val entityScope  	enum (ENTERPRISE | GLOBAL) 	autogenerated
//@JsonProperty("externalID 	string 	filterable   orderable   locally unique
@JsonProperty("lastName 	string 	required   filterable   orderable
@JsonProperty("lastUpdatedBy 	string 	autogenerated
@JsonProperty("mobileNumber 	string 	filterable   orderable
@JsonProperty("password 	string 	required
@JsonProperty("statisticsEnabled 	boolean
@JsonProperty("userName 	string 	required   filterable   orderable
@JsonProperty("VSSStatsInterval 	integer 	filterable   orderable
*/