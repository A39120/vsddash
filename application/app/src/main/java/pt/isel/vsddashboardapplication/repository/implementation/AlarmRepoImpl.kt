package pt.isel.vsddashboardapplication.repository.implementation

import androidx.lifecycle.LiveData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import pt.isel.vsddashboardapplication.communication.services.AlarmServices
import pt.isel.vsddashboardapplication.communication.services.RetrofitServices
import pt.isel.vsddashboardapplication.repository.AlarmRepository
import pt.isel.vsddashboardapplication.repository.dao.NSAlarmDao
import pt.isel.vsddashboardapplication.repository.database.VsdDatabase
import pt.isel.vsddashboardapplication.repository.pojo.Alarm

class AlarmRepoImpl(
    private val dao: NSAlarmDao = VsdDatabase.getInstance()!!.nsAlarmDao(),
    private val service: AlarmServices? = RetrofitServices.getInstance().createVsdService(AlarmServices::class.java)
) : AlarmRepository {

    override suspend fun getAlarm(id: String): LiveData<Alarm> {
        val value = dao.load(id)
        if(value.value == null)
            update(id)

        return value
    }

    override suspend fun getAlarmForNSG(nsgId: String): LiveData<List<Alarm>> {
        val value = dao.loadAll(nsgId)
        if(value.value == null)
            updateAll(nsgId)

        return value
    }

    override suspend fun update(id: String): Unit = withContext(Dispatchers.IO){
        val alarm = service?.getAlarm(id)?.await()
        alarm?.forEach { dao.save(it) }
    }!!



    override suspend fun updateAll(nsgId: String): Unit = withContext(Dispatchers.IO) {
        val alarms = service?.getGatewayAlarms(nsgId)?.await()
        alarms?.forEach { dao.save(it) }
    }!!

}