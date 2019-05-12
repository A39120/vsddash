package pt.isel.vsdashbapi.vsdashapi.communication.http.client.pojo.user

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty
import pt.isel.vsdashbapi.vsdashapi.communication.http.client.pojo.enumerables.EntityScope
import pt.isel.vsdashbapi.vsdashapi.communication.http.client.pojo.enumerables.Role

@JsonIgnoreProperties( ignoreUnknown = true )
data class User(
        @JsonProperty("AARFlowStatsInterval") val AARFlowStatsInterval: Long?,
        @JsonProperty("AARProbeStatsInterval") val AARProbeStatsInterval: Long?,
        @JsonProperty("VSSStatsInterval") val VSSStatsInterval: Long?,
        @JsonProperty("disabled") val disabled: Boolean?,
        @JsonProperty("elasticSearchAddress") val elasticSearchAddress: String?,
        @JsonProperty("email") val email: String?,
        @JsonProperty("enterpriseID") val enterpriseID: String?,
        @JsonProperty("enterpriseName") val enterpriseName: String?,
        @JsonProperty("entityScope") val entityScope: EntityScope?,
        @JsonProperty("externalID") val externalID: String?,
        @JsonProperty("flowCollectionEnabled") val flowCollectionEnabled: Boolean?,
        @JsonProperty("role") val role: Role?,
        @JsonProperty("statisticsEnabled") val statisticsEnabled: Boolean?,
        @JsonProperty("userName") val userName: String?,
        @JsonProperty("APIKey") val apiKey: String?,
        @JsonProperty("APIKeyExpiry") val apiKeyExpiry: Long?
)
