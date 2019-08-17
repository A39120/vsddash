package pt.isel.vsddashboardapplication.repository.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy.IGNORE
import androidx.room.Query
import pt.isel.vsddashboardapplication.model.statistics.DpiProbestats

@Dao
interface DpiProbestatsDao {

    @Insert(onConflict = IGNORE)
    fun save(dpiProbe: DpiProbestats)

    @Insert(onConflict = IGNORE)
    fun saveAll(list : List<DpiProbestats>)

    @Query("SELECT * FROM dpiproberstat WHERE sourceNSG = :nsg AND srcUplink = :port AND timestamp > :start AND timestamp < :end AND aPMGroup IS NULL")
    fun loadOutbound(nsg: String, port: String, start: Long, end: Long) : LiveData<List<DpiProbestats>?>

    @Query("SELECT * FROM  dpiproberstat WHERE destinationNSG = :nsg AND dstUplink = :port AND timestamp > :start AND timestamp < :end AND aPMGroup = :apmGroup")
    fun loadInbound(nsg : String, port: String, apmGroup: String, start: Long, end: Long) : LiveData<List<DpiProbestats>?>

    @Query("SELECT * FROM dpiproberstat WHERE sourceNSG = :nsg AND srcUplink = :port AND timestamp > :start AND timestamp < :end AND aPMGroup = :apmGroup")
    fun loadOutbound(nsg: String, port: String, apmGroup: String, start: Long, end: Long) : LiveData<List<DpiProbestats>?>

    @Query("SELECT * FROM  dpiproberstat WHERE destinationNSG = :nsg AND dstUplink = :port AND timestamp > :start AND timestamp < :end")
    fun loadInbound(nsg : String, port: String, start: Long, end: Long) : LiveData<List<DpiProbestats>?>

    @Delete
    fun delete(vararg probestats: DpiProbestats)

    @Query("DELETE FROM dpiproberstat")
    fun deleteAll()
}