package pt.isel.vsddashboardapplication.repository.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import pt.isel.vsddashboardapplication.model.PerformanceMonitor

@Dao
interface PerformanceMonitorDao : BaseDao<PerformanceMonitor>{

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    override fun save(perfMonitor: PerformanceMonitor)

    @Query("SELECT * FROM perfmonitor WHERE id = :id")
    override fun load(id: String) : LiveData<PerformanceMonitor?>

    @Query("SELECT * FROM perfmonitor WHERE parentID = :enterpriseId")
    fun loadForEnterprise(enterpriseId : String) : LiveData<List<PerformanceMonitor>?>

    @Delete
    override fun delete(vararg perfMonitor: PerformanceMonitor)

    @Query("DELETE FROM perfmonitor")
    override fun deleteAll()

    @Query("DELETE FROM perfmonitor WHERE iD = :id")
    override fun delete(id: String)

}
