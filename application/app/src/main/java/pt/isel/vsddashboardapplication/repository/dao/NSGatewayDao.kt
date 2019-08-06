package pt.isel.vsddashboardapplication.repository.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query
import pt.isel.vsddashboardapplication.model.NSGateway

@Dao
interface NSGatewayDao : BaseDao<NSGateway>{

    @Insert(onConflict = REPLACE)
    override fun save(nsgateway: NSGateway)


    @Query("SELECT * FROM nsgateway WHERE id = :id")
    override fun load(id: String) : LiveData<NSGateway?>

    @Query("SELECT * FROM nsgateway")
    fun loadAll() : LiveData<List<NSGateway>?>


    @Delete
    override fun delete(vararg nsgs: NSGateway)

}