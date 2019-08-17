package pt.isel.vsddashboardapplication.repository.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query
import pt.isel.vsddashboardapplication.model.VRS

@Dao
interface VrsDao : BaseDao<VRS>{

    @Insert(onConflict = REPLACE)
    override fun save(nsgateway: VRS)

    @Query("SELECT * FROM vrs WHERE id = :id")
    override fun load(id: String) : LiveData<VRS?>

    @Query("SELECT * FROM vrs WHERE parentID = :id AND parentType = 'VSC'")
    fun loadForVsc(id : String) : LiveData<List<VRS>?>

    @Query("SELECT * FROM vrs WHERE parentId IS NULL")
    fun loadGlobal() : LiveData<List<VRS>?>

    @Delete
    override fun delete(vararg vrs: VRS)

    @Query("DELETE FROM vrs")
    override fun deleteAll()

}
