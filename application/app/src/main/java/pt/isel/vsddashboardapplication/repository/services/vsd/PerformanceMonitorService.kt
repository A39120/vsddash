package pt.isel.vsddashboardapplication.repository.services.vsd

import kotlinx.coroutines.Deferred
import pt.isel.vsddashboardapplication.model.PerformanceMonitor
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path

interface PerformanceMonitorService {

    @Headers("Accept: application/json")
    @GET("/nuage/api/v5_0/performancemonitors/{id}")
    fun getPerformanceMonitor(@Path("id") parentId: String) : Deferred<List<PerformanceMonitor>?>

    @Headers("Accept: application/json")
    @GET("/nuage/api/v5_0/enterprises/{id}/performancemonitors/")
    fun getPerformanceMonitorsForEnterprise(@Path("id") parentId: String) : Deferred<List<PerformanceMonitor>?>

}
