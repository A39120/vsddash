package pt.isel.vsddashboardapplication.repository

import androidx.lifecycle.LiveData
import pt.isel.vsddashboardapplication.repository.pojo.Alarm

interface AlarmRepository {

    suspend fun getAlarm(id: String) : LiveData<Alarm>

    suspend fun getAlarmForNSG(nsgId: String) : LiveData<List<Alarm>>

    suspend fun update(id: String)

    suspend fun updateAll(nsgId: String)

}