package pt.isel.vsddashboardapplication.repository.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import pt.isel.vsddashboardapplication.model.events.Events

@Dao
interface EventDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun save(events: Events)

    @Query("SELECT * FROM events")
    fun getEvents() : List<Events>

    @Query("DELETE FROM events")
    fun deleteAll()

}/*{

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun save(event: Events)

    @Query("SELECT * FROM events")
    fun getAll() : LiveData<List<Events>>

    @Query("DELETE FROM events")
    fun deleteAll()

}
 */