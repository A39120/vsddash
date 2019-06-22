package pt.isel.vsddashboardapplication.communication.services

import pt.isel.vsddashboardapplication.model.statistics.ESIndex
import retrofit2.http.GET
import retrofit2.http.Headers

interface ElasticSearchService {

    @Headers("Accept: application/json")
    @GET("/_cat/indices?format=json&pretty")
    suspend fun getIndices() : List<ESIndex>

    @Headers("Accept: application/json")
    @GET("/nuage_flow/_search?format=json")
    fun getFlow()

    @Headers("Accept: application/json")
    @GET("/nuage_addressmap/_search?format=json")
    fun getAddressMap()

    @Headers("Accept: application/json")
    @GET("/nuage_dpi_flowstats/_search?format=json")
    fun getDpiFlowstats()

    @Headers("Accept: application/json")
    @GET("/nuage_dpi_probestats/_search?format=json")
    fun getDpiProbestats()

    @Headers("Accept: application/json")
    @GET("/nuage_dpi_slastats/_search?format=json")
    fun getDpiSlastats()

    @Headers("Accept: application/json")
    @GET("/nuage_natt/_search?format=json")
    fun getNatt()

    @Headers("Accept: application/json")
    @GET("/nuage_sysmon/_search?format=json")
    fun getSysmon()

    @Headers("Accept: application/json")
    @GET("/nuage_vlan/_search?format=json")
    fun getVlan()

    @Headers("Accept: application/json")
    @GET("/nuage_vport/_search?format=json")
    fun getVport()

}
