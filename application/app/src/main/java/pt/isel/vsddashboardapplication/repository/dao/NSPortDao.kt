package pt.isel.vsddashboardapplication.repository.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query
import pt.isel.vsddashboardapplication.repository.pojo.NSPort

@Dao
interface NSPortDao {

    @Insert(onConflict = REPLACE)
    fun save(nsgateway: NSPort)

    @Query("SELECT * FROM nsport WHERE id = :id")
    fun load(id: String) : LiveData<NSPort>

    @Query("SELECT * FROM nsport WHERE parentID = :nsgId")
    fun loadForNsg(nsgId : String) : LiveData<List<NSPort>>

    @Delete
    fun delete(vararg nsgs: NSPort)

}
