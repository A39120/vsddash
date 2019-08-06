package pt.isel.vsddashboardapplication.repository.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query
import pt.isel.vsddashboardapplication.model.VSC

@Dao
interface VscDao : BaseDao<VSC>{

    @Insert(onConflict = REPLACE)
    override fun save(vsc: VSC)

    @Query("SELECT * FROM vsc WHERE id = :id")
    override fun load(id: String) : LiveData<VSC?>

    @Query("SELECT * FROM vsc WHERE parentID = :id")
    fun loadForVsp(id : String) : LiveData<List<VSC>?>

    @Query("SELECT * FROM vsc")
    fun loadAll() : LiveData<List<VSC>?>

    @Delete
    override fun delete(vararg vsc: VSC)

}
