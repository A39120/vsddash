package pt.isel.vsddashboardapplication.repository.base.implementation

import android.util.Log
import androidx.lifecycle.LiveData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import pt.isel.vsddashboardapplication.model.Alarm
import pt.isel.vsddashboardapplication.repository.base.NSPortRepository
import pt.isel.vsddashboardapplication.repository.dao.NSPortDao
import pt.isel.vsddashboardapplication.model.NSPort
import pt.isel.vsddashboardapplication.repository.dao.AlarmDao
import pt.isel.vsddashboardapplication.repository.services.RetrofitSingleton
import javax.inject.Inject

/**
 * The implementation of a NSPortRepository
 */
class NSPortRepositoryImpl @Inject constructor(
    private val dao: NSPortDao,
    private val alarmDao: AlarmDao
) : NSPortRepository {
    companion object{ private const val TAG = "REPO/NSPORT" }

    override fun getAlarms(id: String): LiveData<List<Alarm>?> {
        Log.d(TAG, "Getting the list of alarms for NSPort $id")
        return alarmDao.loadAll(id)
    }

    override suspend fun updateAlarms(id: String, onFinish: (() -> Unit)?) {
        Log.d(TAG, "Getting alarms for port $id")
        val deferred = RetrofitSingleton
            .nsportServices()
            ?.getPortAlarms(id)

        withContext(Dispatchers.IO){
            deferred?.await()?.forEach {
                Log.d(TAG, "Saving alarm ${it.iD}")
                alarmDao.save(it)
            }
        }
    }

    /**
     * Gets the port from the DB/Network
     * @param id: the ID of a Port
     * @return the LiveData of a NSPort
     */
    override fun get(id: String): LiveData<NSPort?> {
        Log.d(TAG, "Getting NSPort $id")
        val port = dao.load(id)
        return port
    }


    /**
     * Gets all the ports of a NSG
     * @param parentId: The ID of a existing NSG
     */
    override fun getAll(parentId: String): LiveData<List<NSPort>?> {
        Log.d(TAG, "Getting NSPort of NSG $parentId")
        val ports = dao.loadForNsg(parentId)
        return ports
    }

    /**
     * Updates DB by collecting if possible the information from a network call
     * @param id: the ID of a existing port
     */
    override suspend fun update(id: String, onFinish: (() -> Unit)?) {
        Log.d(TAG, "Updating NSPort $id")
        withContext(Dispatchers.IO) {
            val port = RetrofitSingleton
                .nsportServices()
                ?.getPort(id)
                ?.await()

            port?.forEach { dao.save(it) }
            onFinish?.invoke()
            return@withContext
        }
    }


    /**
     * Updates all the ports of a determined NSG according to the network service
     * if possible.
     *
     * @param parentId: the ID of a NSG
     */
    override suspend fun updateAll(parentId: String, onFinish: (() -> Unit)?) {
        Log.d(TAG, "Updating NSPorts of NSG $parentId")
        withContext(Dispatchers.IO) {
            val ports = RetrofitSingleton
                .nsportServices()
                ?.getGatewayPorts(parentId)
                ?.await()

            ports?.forEach { dao.save(it) }
            onFinish?.invoke()
            return@withContext
        }
    }

}