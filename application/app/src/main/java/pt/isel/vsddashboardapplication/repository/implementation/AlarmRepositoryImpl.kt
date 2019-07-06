package pt.isel.vsddashboardapplication.repository.implementation

import android.util.Log
import androidx.lifecycle.LiveData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import pt.isel.vsddashboardapplication.repository.services.vsd.AlarmServices
import pt.isel.vsddashboardapplication.repository.services.RetrofitServices
import pt.isel.vsddashboardapplication.model.Alarm
import pt.isel.vsddashboardapplication.repository.AlarmRepository
import pt.isel.vsddashboardapplication.repository.dao.NSAlarmDao
import javax.inject.Inject

/**
 * Repository for handling the alarm. Update and Get.
 */
class AlarmRepositoryImpl @Inject constructor(
    private val dao: NSAlarmDao
) : AlarmRepository {
    companion object {
        private const val TAG = "REPO/ALARM"
    }

    private val service: AlarmServices? = RetrofitServices.getInstance().createVsdService(
        AlarmServices::class.java)

    /**
     * Gets an alarm
     * @param id: the alarm ID
     * @return the LiveData containing the alarm
     */
    override suspend fun get(id: String): LiveData<Alarm> {
        Log.d(TAG, "Getting Alarm ($id) from repository")
        val value = dao.load(id)
        if(value.value == null)
            update(id)

        return value
    }

    /**
     * Gets the alarms for a given NSG
     * @param parentId: the ID of an NSG
     * @return LiveData with the list of alarms
     */
    override suspend fun getAll(parentId: String): LiveData<List<Alarm>> {
        Log.d(TAG, "Getting list of Alarms of NSG $parentId")
        val value = dao.loadAll(parentId)
        if(value.value == null)
            updateAll(parentId)

        return value
    }

    /**
     * Updates an alarm
     * @param id: Alarm ID
     */
    override suspend fun update(id: String) {
        withContext(Dispatchers.IO) {
            Log.d(TAG, "Updating Alarm $id")
            val alarm = service?.getAlarm(id)?.await()
            alarm?.forEach { dao.save(it) }
        }
    }

    /**
     * Updates the list of alarms of a given NSG
     * @param parentId: the id of the NSG that owns the alarms
     */
    override suspend fun updateAll(parentId: String) {
        withContext(Dispatchers.IO) {
            Log.d(TAG, "Updating Alarms of NSG $parentId")
            val alarms = service?.getGatewayAlarms(parentId)?.await()
            alarms?.forEach { dao.save(it) }
        }
    }

}