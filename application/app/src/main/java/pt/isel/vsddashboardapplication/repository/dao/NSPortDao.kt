package pt.isel.vsddashboardapplication.repository.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query
import pt.isel.vsddashboardapplication.model.NSPort

@Dao
interface NSPortDao : BaseDao<NSPort>{

    @Insert(onConflict = REPLACE)
    override fun save(nsgateway: NSPort)

    @Query("SELECT * FROM nsport WHERE id = :id")
    override fun load(id: String) : LiveData<NSPort?>

    @Query("SELECT * FROM nsport WHERE parentID = :nsgId")
    fun loadForNsg(nsgId : String) : LiveData<List<NSPort>?>

    @Query("DELETE FROM nsport WHERE parentID = :nsgId")
    fun deleteNsgPorts(nsgId : String)

    @Delete
    override fun delete(vararg nsgs: NSPort)

    @Query("DELETE FROM nsport")
    override fun deleteAll()

}
