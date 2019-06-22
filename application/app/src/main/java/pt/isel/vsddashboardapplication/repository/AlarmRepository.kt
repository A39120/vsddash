package pt.isel.vsddashboardapplication.repository

import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.LiveData
import pt.isel.vsddashboardapplication.model.Alarm

interface AlarmRepository : LifecycleObserver{

    suspend fun getAlarm(id: String) : LiveData<Alarm>

    suspend fun getAlarmForNSG(nsgId: String) : LiveData<List<Alarm>>

    suspend fun update(id: String)

    suspend fun updateAll(nsgId: String)

}