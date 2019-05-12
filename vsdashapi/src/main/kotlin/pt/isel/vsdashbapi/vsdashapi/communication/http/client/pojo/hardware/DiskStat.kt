package pt.isel.vsdashbapi.vsdashapi.communication.http.client.pojo.hardware

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty
import net.nuagenetworks.bambou.annotation.RestEntity
import pt.isel.vsdashbapi.vsdashapi.communication.http.client.pojo.enumerables.DiskStatEnumerables
import pt.isel.vsdashbapi.vsdashapi.communication.http.client.pojo.enumerables.EntityScope

@JsonIgnoreProperties(ignoreUnknown = true)
@RestEntity(restName = "diskstats", resourceName = "diskstats")
data class DiskStat(
        @JsonProperty("available") val available: Float? = null,
        @JsonProperty("entityScope") val entityScope: EntityScope? = EntityScope.ENTERPRISE,
        @JsonProperty("externalID") val externalID: String? = null,
        @JsonProperty("name") val name: String? = null,
        @JsonProperty("size") val size: Float? = null,
        @JsonProperty("unit") val unit: DiskStatEnumerables.Unit? = null,
        @JsonProperty("used") val used: Float? = null
)

