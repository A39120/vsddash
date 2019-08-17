package pt.isel.vsddashboardapplication.repository.base.implementation

import android.util.Log
import androidx.lifecycle.LiveData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import pt.isel.vsddashboardapplication.model.Alarm
import pt.isel.vsddashboardapplication.repository.base.AlarmRepository
import pt.isel.vsddashboardapplication.repository.dao.AlarmDao
import pt.isel.vsddashboardapplication.repository.services.RetrofitSingleton
import javax.inject.Inject

/**
 * Repository for handling the alarm. Update and Get.
 */
class AlarmRepositoryImpl @Inject constructor(
    private val dao: AlarmDao
) : AlarmRepository {
    companion object {
        private const val TAG = "REPO/ALARM"
    }

    /**
     * Gets an alarm
     * @param id: the alarm ID
     * @return the LiveData containing the alarm
     */
    override fun get(id: String): LiveData<Alarm?> {
        Log.d(TAG, "Getting Alarm ($id) from repository")
        return dao.load(id)
    }

    /**
     * Gets the alarms for a given NSG
     * @param parentId: the ID of an NSG
     * @return LiveData with the list of alarms
     */
    override fun getAll(parentId: String): LiveData<List<Alarm>?> {
        Log.d(TAG, "Getting list of Alarms of NSG $parentId")
        return dao.loadAll(parentId)
    }

    /**
     * Updates an alarm
     * @param id: Alarm ID
     */
    override suspend fun update(id: String, onFinish: (() -> Unit)?) {
        withContext(Dispatchers.IO) {
            Log.d(TAG, "Updating Alarm $id")
            val alarm = RetrofitSingleton
                .alarmServices()
                ?.getAlarm(id)?.let {
                    try {
                        val res = it.await()
                        res?.forEach { al -> dao.save(al) }
                    } finally {
                        onFinish?.invoke()
                    }
                    return@withContext
                }
        }
    }

    /**
     * Updates the list of alarms of a given NSG
     * @param parentId: the id of the NSG that owns the alarms
     */
    override suspend fun updateAll(parentId: String, onFinish: (() -> Unit)?) {
        Log.d(TAG, "Updating Alarms of NSG $parentId")
        RetrofitSingleton.alarmServices()
            ?.getGatewayAlarms(parentId)?.let {
                withContext(Dispatchers.IO) {
                    try {
                        val res = it.await()
                        res?.forEach { dao.save(it) }
                    } finally {
                        onFinish?.invoke()
                    }
                    return@withContext
                }
            }
    }

}


