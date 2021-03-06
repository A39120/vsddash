package pt.isel.vsddashboardapplication.repository.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query
import pt.isel.vsddashboardapplication.model.APM

@Dao
interface ApmDao : BaseDao<APM>{
    @Insert(onConflict = REPLACE)
    override fun save(apm: APM)

    @Query("SELECT * FROM apm WHERE id = :id")
    override fun load(id: String): LiveData<APM?>

    @Query("SELECT * FROM apm WHERE parentID = :enterpriseId")
    fun loadAll(enterpriseId: String): LiveData<List<APM>?>

    @Delete
    override fun delete(vararg apm: APM)

    @Query("DELETE FROM apm")
    override fun deleteAll()

    @Query("DELETE FROM apm WHERE iD = :id")
    override fun delete(id: String)

}
