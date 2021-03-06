package pt.isel.vsddashboardapplication.repository.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query
import pt.isel.vsddashboardapplication.model.NSGInfo

@Dao
interface NSGInfoDao : BaseDao<NSGInfo>{

    @Insert(onConflict = REPLACE)
    override fun save(nsgateway: NSGInfo)

    @Query("SELECT * FROM nsginfo WHERE parentID = :id")
    override fun load(id: String) : LiveData<NSGInfo?>

    @Delete
    override fun delete(vararg nsgs: NSGInfo)

    @Query("DELETE FROM nsginfo WHERE iD = :id")
    override fun delete(id: String)

    @Query("DELETE FROM nsginfo")
    override fun deleteAll()

}
