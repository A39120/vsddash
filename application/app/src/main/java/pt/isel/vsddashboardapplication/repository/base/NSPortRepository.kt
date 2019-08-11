package pt.isel.vsddashboardapplication.repository.base

import androidx.lifecycle.LiveData
import pt.isel.vsddashboardapplication.model.Alarm
import pt.isel.vsddashboardapplication.model.NSPort

/**
 * Repository responsible for getting the information of one port or
 * more related to the NSG
 */
interface NSPortRepository : IBaseRepository<NSPort> {

    fun getAlarms(id: String) : LiveData<List<Alarm>?>

    suspend fun updateAlarms(id: String, onFinish: (() -> Unit)? = null)

}
