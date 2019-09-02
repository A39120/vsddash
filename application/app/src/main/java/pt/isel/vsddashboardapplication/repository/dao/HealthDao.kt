package pt.isel.vsddashboardapplication.repository.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import pt.isel.vsddashboardapplication.model.Health

@Dao
interface HealthDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun save(health: Health)

    @Query("SELECT * FROM health")
    fun load() : LiveData<Health?>

    @Query("DELETE FROM health")
    fun delete()

}