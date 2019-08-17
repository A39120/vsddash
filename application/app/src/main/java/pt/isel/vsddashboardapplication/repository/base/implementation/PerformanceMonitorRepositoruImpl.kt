package pt.isel.vsddashboardapplication.repository.base.implementation

import androidx.lifecycle.LiveData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import pt.isel.vsddashboardapplication.model.PerformanceMonitor
import pt.isel.vsddashboardapplication.repository.base.PerformanceMonitorRepository
import pt.isel.vsddashboardapplication.repository.dao.PerformanceMonitorDao
import pt.isel.vsddashboardapplication.repository.services.RetrofitSingleton
import javax.inject.Inject

class PerformanceMonitorRepositoruImpl @Inject constructor(
    private val dao: PerformanceMonitorDao
) : PerformanceMonitorRepository {

    override fun get(id: String): LiveData<PerformanceMonitor?> =
        dao.load(id)


    override fun getAll(enterpriseId: String): LiveData<List<PerformanceMonitor>?> =
        dao.loadForEnterprise(enterpriseId)

    override suspend fun update(id: String, onFinish: (() -> Unit)?) {
        val def = RetrofitSingleton
            .performanceMonitorService()
            ?.getPerformanceMonitor(id)

        withContext(Dispatchers.IO){
            def?.await()?.forEach { pm -> dao.save(pm) }
            onFinish?.invoke()
        }
    }


    override suspend fun updateAll(enterpriseId: String, onFinish: (() -> Unit)?) {
        val def = RetrofitSingleton
            .performanceMonitorService()
            ?.getPerformanceMonitorsForEnterprise(enterpriseId)

        withContext(Dispatchers.IO){
            def?.await()?.forEach { pm -> dao.save(pm) }
            onFinish?.invoke()
        }
    }
}