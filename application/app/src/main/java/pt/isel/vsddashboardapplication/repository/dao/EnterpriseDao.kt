package pt.isel.vsddashboardapplication.repository.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query
import pt.isel.vsddashboardapplication.model.Enterprise

@Dao
interface EnterpriseDao : BaseDao<Enterprise> {

    @Insert(onConflict = REPLACE)
    override fun save(enterprise: Enterprise)

    @Query("SELECT * FROM enterprise WHERE id = :id")
    override fun load(id: String) : LiveData<Enterprise?>

    @Query("SELECT * FROM enterprise WHERE user = :userId AND organization = :organization AND vsd = :vsd")
    fun loadAll(userId: String, organization: String, vsd: String) : LiveData<List<Enterprise>?>

    @Delete
    override fun delete(vararg alarm: Enterprise)

}