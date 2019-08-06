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

    @Query("SELECT * FROM vsp")
    fun loadAll() : LiveData<List<VSP>?>

    @Delete
    override fun delete(vararg vsp: VSP)

}
