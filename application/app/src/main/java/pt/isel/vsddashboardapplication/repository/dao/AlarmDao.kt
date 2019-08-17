package pt.isel.vsddashboardapplication.repository.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query
import pt.isel.vsddashboardapplication.model.Alarm

/**
 * Gets all NSGateway alarms for a determined gateway
 */
@Dao
interface AlarmDao : BaseDao<Alarm>{

    @Insert(onConflict = REPLACE)
    override fun save(alarm: Alarm)

    @Query("SELECT * FROM alarm WHERE id = :id")
    override fun load(id: String) : LiveData<Alarm?>

    @Query("SELECT * FROM alarm WHERE parentID = :nsg")
    fun loadAll(nsg : String) : LiveData<List<Alarm>?>

    @Delete
    override fun delete(vararg alarm: Alarm)

    @Query("DELETE FROM alarm")
    override fun deleteAll()

}
