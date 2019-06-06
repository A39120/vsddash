package pt.isel.vsddashboardapplication.repository.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query
import pt.isel.vsddashboardapplication.repository.pojo.NSGateway

@Dao
interface NSGatewayDao {

    @Insert(onConflict = REPLACE)
    fun save(nsgateway: NSGateway)


    @Query("SELECT * FROM nsgateway WHERE id = :id")
    fun load(id: String) : LiveData<NSGateway>

    @Query("SELECT * FROM nsgateway")
    fun loadAll() : LiveData<List<NSGateway>>


    @Delete
    fun delete(vararg nsgs: NSGateway)

}