package pt.isel.vsddashboardapplication.repository.base

import androidx.lifecycle.LiveData
import pt.isel.vsddashboardapplication.model.Alarm

interface AlarmParentRepository {

    fun getAlarms(id: String) : LiveData<List<Alarm>?>

    suspend fun updateAlarms(id: String, onFinish: (() -> Unit)? = null)

}