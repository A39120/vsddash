package pt.isel.vsddashboardapplication.repository.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query
import pt.isel.vsddashboardapplication.model.Enterprise

@Dao
interface EnterpriseDao {

    @Insert(onConflict = REPLACE)
    fun save(enterprise: Enterprise)

    @Query("SELECT * FROM enterprise WHERE id = :id")
    fun load(id: String) : LiveData<Enterprise?>

    @Query("SELECT * FROM enterprise WHERE userId = :userId")
    fun loadAll(userId: String) : LiveData<List<Enterprise>?>

    @Delete
    fun delete(vararg alarm: Enterprise)

}