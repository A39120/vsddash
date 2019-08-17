package pt.isel.vsddashboardapplication.repository.services.es

import kotlinx.coroutines.Deferred
import pt.isel.vsddashboardapplication.model.statistics.Sysmon
import pt.isel.vsddashboardapplication.model.statistics.base.Search
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface SysmonServices {

    @Headers("Accept: application/json")
    @GET("/nuage_sysmon/_search?format=json")
    fun getSysmon(@Query("q") query: Array<String> = arrayOf("*"),
                  @Query("sort") sort: String? = null,
                  @Query("from") offset: Int? = 0,
                  @Query("size") size: Int? = 10)
            : Deferred<Search<Sysmon>>

    @Headers("Accept: application/json")
    @GET("/nuage_sysmon/_search?format=json")
    fun getSysmonWithQuery(@Query("q") query: Array<String> = arrayOf("*"),
                           @Query("sort") sort: String? = null,
                           @Query("from") offset: Int? = 0,
                           @Query("size") size: Int? = 10)
            : Deferred<Search<Sysmon>>

    @Headers("Accept: application/json")
    @GET("/nuage_sysmon/_search?format=json")
    fun getSysmonWithQuery(
        @Query("q") query: String = "*",
        @Query("sort") sort: String? = null,
        @Query("from") offset: Int? = 0,
        @Query("size") size: Int? = 10) : Deferred<Search<Sysmon>>

}
