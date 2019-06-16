package pt.isel.vsddashboardapplication.repository.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query
import pt.isel.vsddashboardapplication.repository.pojo.Alarm

/**
 * Gets all NSGateway alarms for a determined gateway
 */
@Dao
interface NSAlarmDao {

    @Insert(onConflict = REPLACE)
    fun save(alarm: Alarm)

    @Query("SELECT * FROM alarm WHERE id = :id")
    fun load(id: String) : LiveData<Alarm>

    @Query("SELECT * FROM alarm WHERE parentID = :nsg")
    fun loadAll(nsg : String) : LiveData<List<Alarm>>

    @Delete
    fun delete(vararg alarm: Alarm)

}
