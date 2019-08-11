package pt.isel.vsddashboardapplication.repository.base

import androidx.lifecycle.LiveData
import pt.isel.vsddashboardapplication.model.Alarm
import pt.isel.vsddashboardapplication.model.VSC

interface VscRepository : IBaseRepository<VSC> {

    fun getAlarms(parent: String) : LiveData<List<Alarm>?>

    suspend fun updateAlarms(parent: String, onFinish: (() -> Unit)? = null)
}