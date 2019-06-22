package pt.isel.vsddashboardapplication.repository.implementation

import androidx.lifecycle.LiveData
import kotlinx.coroutines.*
import pt.isel.vsddashboardapplication.communication.services.AlarmServices
import pt.isel.vsddashboardapplication.communication.services.RetrofitServices
import pt.isel.vsddashboardapplication.repository.AlarmRepository
import pt.isel.vsddashboardapplication.repository.dao.NSAlarmDao
import pt.isel.vsddashboardapplication.repository.database.VsdDatabase
import pt.isel.vsddashboardapplication.model.Alarm
import javax.inject.Inject

/**
 * Repository for handling the alarm. Update and Get.
 */
class AlarmRepoImpl @Inject constructor(
    private val dao: NSAlarmDao
) : AlarmRepository {

    private val service: AlarmServices? = RetrofitServices.getInstance().createVsdService(AlarmServices::class.java)

    /**
     * Gets an alarm
     * @param id: the alarm ID
     * @return the LiveData containing the alarm
     */
    override suspend fun getAlarm(id: String): LiveData<Alarm> {
        val value = dao.load(id)
        if(value.value == null)
            update(id)

        return value
    }

    /**
     * Gets the alarms for a given NSG
     * @param nsgId: the ID of an NSG
     * @return LiveData with the list of alarms
     */
    override suspend fun getAlarmForNSG(nsgId: String): LiveData<List<Alarm>> {
        val value = dao.loadAll(nsgId)
        if(value.value == null)
            updateAll(nsgId)

        return value
    }

    /**
     * Updates an alarm
     * @param id: Alarm ID
     */
    override suspend fun update(id: String) {
        withContext(Dispatchers.IO) {
            val alarm = service?.getAlarm(id)?.await()
            alarm?.forEach { dao.save(it) }
        }
    }

    /**
     * Updates the list of alarms of a given NSG
     * @param nsgId: the id of the NSG that owns the alarms
     */
    override suspend fun updateAll(nsgId: String) {
        withContext(Dispatchers.IO) {
            val alarms = service?.getGatewayAlarms(nsgId)?.await()
            alarms?.forEach { dao.save(it) }
        }
    }

}