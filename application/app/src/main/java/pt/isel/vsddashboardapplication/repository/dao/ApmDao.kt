package pt.isel.vsddashboardapplication.repository.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query
import pt.isel.vsddashboardapplication.model.APM

@Dao
interface ApmDao {
    @Insert(onConflict = REPLACE)
    fun save(apm: APM)

    @Query("SELECT * FROM apm WHERE id = :id")
    fun load(id: String): LiveData<APM?>

    @Query("SELECT * FROM apm WHERE parentID = :enterpriseId")
    fun loadAll(enterpriseId: String): LiveData<List<APM>?>

    @Delete
    fun delete(vararg apm: APM)
}
