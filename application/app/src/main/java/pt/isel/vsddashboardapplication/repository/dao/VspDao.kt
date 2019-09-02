package pt.isel.vsddashboardapplication.repository.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query
import pt.isel.vsddashboardapplication.model.VSP

@Dao
interface VspDao : BaseDao<VSP>{

    @Insert(onConflict = REPLACE)
    override fun save(vsp: VSP)

    @Query("SELECT * FROM vsp WHERE id = :id")
    override fun load(id: String) : LiveData<VSP?>

    @Query("DELETE FROM vsp WHERE id = :id")
    override fun delete(id: String)

    @Query("SELECT * FROM vsp")
    fun loadAll() : LiveData<List<VSP>?>

    @Query("DELETE FROM vsp")
    override fun deleteAll()

    @Delete
    override fun delete(vararg vsp: VSP)

}
