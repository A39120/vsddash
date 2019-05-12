package pt.isel.vsdashbapi.vsdashapi.communication.http.client.pojo.user


import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty
import net.nuagenetworks.bambou.annotation.RestEntity
import pt.isel.vsdashbapi.vsdashapi.communication.http.client.pojo.enumerables.EntityScope
import pt.isel.vsdashbapi.vsdashapi.communication.http.client.pojo.enumerables.Role

@JsonIgnoreProperties(ignoreUnknown = true)
@RestEntity(restName = "group", resourceName = "groups")
data class Group(
        @JsonProperty("LDAPGroupDN") val LDAPGroupDN: String?,
        @JsonProperty("accountRestrictions")  val accountRestrictions: Boolean?,
        @JsonProperty("description") val description: String?,
        @JsonProperty("entityScope") val entityScope: EntityScope?,
        @JsonProperty("externalID") val externalID: String?,
        @JsonProperty("name") val name: String?,
        @JsonProperty("private") val private_: Boolean?,
        @JsonProperty("restrictionDate") val restrictionDate: Float?,
        @JsonProperty("role") val role: Role?
)

