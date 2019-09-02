package pt.isel.vsddashboardapplication.repository.dao

import androidx.room.Dao

@Dao
interface EventDao /*{

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun save(event: Events)

    @Query("SELECT * FROM events")
    fun getAll() : LiveData<List<Events>>

    @Query("DELETE FROM events")
    fun deleteAll()

}
 */