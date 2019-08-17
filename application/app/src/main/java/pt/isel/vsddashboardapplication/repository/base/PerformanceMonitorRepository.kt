package pt.isel.vsddashboardapplication.repository.base

import androidx.lifecycle.LiveData
import pt.isel.vsddashboardapplication.model.PerformanceMonitor

interface PerformanceMonitorRepository {

    fun get(id: String) : LiveData<PerformanceMonitor?>

    fun getAll(enterpriseId: String) : LiveData<List<PerformanceMonitor>?>

    suspend fun update(id: String, onFinish: (() -> Unit)? = null)

    suspend fun updateAll(enterpriseId: String, onFinish: (() -> Unit)? = null)

}