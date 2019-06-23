package pt.isel.vsddashboardapplication.communication.services

import kotlinx.coroutines.Deferred
import pt.isel.vsddashboardapplication.model.statistics.*
import retrofit2.http.GET
import retrofit2.http.Headers

interface ElasticSearchService {

    @Headers("Accept: application/json")
    @GET("/_cat/indices?format=json&pretty")
    suspend fun getIndices() : Deferred<List<ESIndex>>

    @Headers("Accept: application/json")
    @GET("/nuage_flow/_search?format=json")
    fun getFlow() : Deferred<List<Flow>>

    @Headers("Accept: application/json")
    @GET("/nuage_addressmap/_search?format=json")
    fun getAddressMap() : Deferred<List<AddressMap>>

    @Headers("Accept: application/json")
    @GET("/nuage_dpi_flowstats/_search?format=json")
    fun getDpiFlowstats() : Deferred<List<DpiFlowstats>>

    @Headers("Accept: application/json")
    @GET("/nuage_dpi_probestats/_search?format=json")
    fun getDpiProbestats() : Deferred<List<DpiProbestats>>

    @Headers("Accept: application/json")
    @GET("/nuage_dpi_slastats/_search?format=json")
    fun getDpiSlastats() : Deferred<List<DpiSlaStatus>>

    @Headers("Accept: application/json")
    @GET("/nuage_natt/_search?format=json")
    fun getNatt() : Deferred<List<Natt>>

    @Headers("Accept: application/json")
    @GET("/nuage_sysmon/_search?format=json")
    fun getSysmon() : Deferred<List<Sysmon>>

    @Headers("Accept: application/json")
    @GET("/nuage_vlan/_search?format=json")
    fun getVlan() : Deferred<List<Vlan>>

    @Headers("Accept: application/json")
    @GET("/nuage_vport/_search?format=json")
    fun getVport()

}
