package pt.isel.vsddashboardapplication.repository.services.es

import kotlinx.coroutines.Deferred
import pt.isel.vsddashboardapplication.model.statistics.*
import pt.isel.vsddashboardapplication.model.statistics.base.Search
import retrofit2.http.GET
import retrofit2.http.Headers

interface ElasticSearchService {

    @Headers("Accept: application/json")
    @GET("/_cat/indices?format=json&pretty")
    suspend fun getIndices() : Deferred<Search<ESIndex>>

    @Headers("Accept: application/json")
    @GET("/nuage_flow/_search?format=json")
    fun getFlow() : Deferred<Search<Flow>>

    @Headers("Accept: application/json")
    @GET("/nuage_addressmap/_search?format=json")
    fun getAddressMap() : Deferred<Search<AddressMap>>

    @Headers("Accept: application/json")
    @GET("/nuage_dpi_flowstats/_search?q=*&sort=timestamp:desc&format=json")
    fun getDpiFlowstats() : Deferred<Search<DpiFlowstats>>

    @Headers("Accept: application/json")
    @GET("/nuage_dpi_flowstats/_search?q=*&sort=timestamp:desc&format=json")
    fun getDpiFlowstatsFromNsg() : Deferred<Search<DpiFlowstats>>

    @Headers("Accept: application/json")
    @GET("/nuage_dpi_probestats/_search?q=*&sort=timestamp:desc&format=json")
    fun getDpiProbestats() : Deferred<Search<DpiProbestats>>



    @Headers("Accept: application/json")
    @GET("/nuage_dpi_slastats/_search?format=json")
    fun getDpiSlastats() : Deferred<Search<DpiSlaStatus>>

    @Headers("Accept: application/json")
    @GET("/nuage_natt/_search?format=json")
    fun getNatt() : Deferred<Search<Natt>>

    @Headers("Accept: application/json")
    @GET("/nuage_sysmon/_search?format=json")

    fun getSysmon() : Deferred<Search<Sysmon>>

    @Headers("Accept: application/json")
    @GET("/nuage_vlan/_search?format=json")
    fun getVlan() : Deferred<Search<Vlan>>

    @Headers("Accept: application/json")
    @GET("/nuage_vport/_search?format=json")
    fun getVport() : Deferred<Search<Vport>>

}
