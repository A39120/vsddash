package pt.isel.vsddashboardapplication.repository.services.es

import kotlinx.coroutines.Deferred
import okhttp3.internal.http.HttpMethod
import pt.isel.vsddashboardapplication.model.es.QueryDSL
import pt.isel.vsddashboardapplication.model.statistics.DpiProbestats
import pt.isel.vsddashboardapplication.model.statistics.base.Search
import retrofit2.http.*

interface DpiProbestatsServices {

    @Headers("Accept: application/json")
    @GET("/nuage_dpi_probestats/_search?format=json")
    fun getDpiProbestats( @Query("q") query: Array<String> = arrayOf("*"),
                          @Query("sort") sort: String? = null,
                          @Query("from") offset: Int? = 0,
                          @Query("size") size: Int? = 10)
            : Deferred<Search<DpiProbestats>>

    @Headers("Accept: application/json")
    @GET("/nuage_dpi_probestats/_search?format=json")
    fun getDpiProbestatsWithQuery( @Query("q") query: Array<String> = arrayOf("*"),
                          @Query("sort") sort: String? = null,
                          @Query("from") offset: Int? = 0,
                          @Query("size") size: Int? = 10)
            : Deferred<Search<DpiProbestats>>

    @Headers("Accept: application/json")
    @GET("/nuage_dpi_probestats/_search?format=json")
    fun getDpiProbestatsWithQuery( @Query("q") query: String = "*",
                                   @Query("sort") sort: String? = null,
                                   @Query("from") offset: Int? = 0,
                                   @Query("size") size: Int? = 10)
            : Deferred<Search<DpiProbestats>>
}
