package pt.isel.vsddashboardapplication.repository.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import pt.isel.vsddashboardapplication.model.statistics.Sysmon

@Dao
interface SysmonDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun save(sysmon: Sysmon)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun saveAll(list : List<Sysmon>)

    @Query("SELECT * FROM sysmon WHERE systemId = :id")
    fun load(id: String): LiveData<List<Sysmon>>

    @Delete
    fun delete(vararg sysmon: Sysmon)
}