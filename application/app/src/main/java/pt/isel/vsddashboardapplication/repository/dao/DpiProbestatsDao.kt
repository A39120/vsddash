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

    @Query("SELECT * FROM dpiproberstat WHERE srcNSG = :nsg AND srcUplink = :port AND timestamp > :start AND timestamp < :end")
    fun load(nsg: String, port: String, start: Long, end: Long) : LiveData<List<DpiProbestats>>

    @Query("SELECT * FROM  dpiproberstat WHERE dstNSG = :nsg AND dstUplink = :port AND timestamp > :start AND timestamp < :end")
    fun loadAll(nsg : String, port: String, start: Long, end: Long) : LiveData<List<DpiProbestats>>

    @Delete
    fun delete(vararg probestats: DpiProbestats)
}