package pt.isel.vsddashboardapplication.repository.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import pt.isel.vsddashboardapplication.model.VPort

@Dao
interface VPortDao : BaseDao<VPort>{

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    override fun save(vport: VPort)

    @Query("SELECT * FROM vport WHERE id = :id")
    override fun load(id: String) : LiveData<VPort?>

    @Query("SELECT * FROM vport WHERE vrs = :id")
    fun loadAll(id : String) : LiveData<List<VPort>?>

    @Delete
    override fun delete(vararg ports: VPort)

    @Query("DELETE FROM vport")
    override fun deleteAll()

    @Query("DELETE FROM vport WHERE iD = :id")
    override fun delete(id: String)

}
