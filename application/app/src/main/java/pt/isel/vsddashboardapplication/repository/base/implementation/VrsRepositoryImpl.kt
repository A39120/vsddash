package pt.isel.vsddashboardapplication.repository.base.implementation

import android.util.Log
import androidx.lifecycle.LiveData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import pt.isel.vsddashboardapplication.model.Alarm
import pt.isel.vsddashboardapplication.model.VPort
import pt.isel.vsddashboardapplication.model.VRS
import pt.isel.vsddashboardapplication.repository.base.VrsRepository
import pt.isel.vsddashboardapplication.repository.dao.AlarmDao
import pt.isel.vsddashboardapplication.repository.dao.VPortDao
import pt.isel.vsddashboardapplication.repository.dao.VrsDao
import pt.isel.vsddashboardapplication.repository.services.RetrofitSingleton
import javax.inject.Inject

/**
 * VRS repository for getting VRS;
 */
class VrsRepositoryImpl @Inject constructor(
    private val dao: VrsDao,
    private val alarmDao: AlarmDao,
    private val  vportDao: VPortDao
): VrsRepository {

    override fun getVports(parent: String): LiveData<List<VPort>?> {
        Log.d(TAG, "Getting vports for VRS $parent")
        return vportDao.loadAll(parent)
    }

    override suspend fun updateVports(parent: String, onFinish: (() -> Unit)?) {
        Log.d(TAG, "Updating VPorts for VRS $parent")

        val def = RetrofitSingleton
            .vportServices()
            ?.getFromVrss(parent)

        withContext(Dispatchers.IO){
            def?.await()?.forEach{
                it.vrs = parent
                vportDao.save(it)
            }
            onFinish?.invoke()
        }
    }

    private companion object val TAG = "REPO/VRS"

    override fun getAlarms(parent: String): LiveData<List<Alarm>?> {
        Log.d(TAG, "Getting alarms for VRS $parent")
        return alarmDao.loadAll(parent)
    }

    override suspend fun updateAlarms(parent: String, onFinish: (() -> Unit)?) {
        Log.d(TAG, "Updating alarms for VRS $parent")
        val deferred = RetrofitSingleton
            .vrsService()
            ?.getVrsAlarms(parent)

        withContext(Dispatchers.IO){
            val result = deferred?.await()
            Log.d(TAG, "Got ${result?.size?:0} alarms")
            result?.forEach { alarm -> alarmDao.save(alarm) }

            onFinish?.invoke()
        }
    }


    /**
     * @return the list of all VRSs
     */
    override fun getGlobal(): LiveData<List<VRS>?> {
        return dao.loadGlobal()
    }

    /**
     * Gets the VRS given an id
     * @param id: The VRS ID
     * @return Live Data containing the VRS
     */
    override fun get(id: String): LiveData<VRS?> {
        return dao.load(id)
    }

    /**
     * Gets all VRS given a VSC
     * @param parentId: the VSC ID
     * @return Live Data containing the list of VRSs
     */
    override fun getAll(parentId: String): LiveData<List<VRS>?> {
        return dao.loadForVsc(parentId)
    }

    /**
     * Updates the VRS
     * @param id: the VRS ID
     * @param onFinish: the callback called upon ending the update
     */
    override suspend fun update(id: String, onFinish: (() -> Unit)?) {
        val await = RetrofitSingleton
            .vrsService()
            ?.getVrs(id)

        withContext(Dispatchers.IO){
            val completed = await?.await()
            completed?.forEach { dao.save(it) }
            onFinish?.invoke()
        }
    }

    /**
     * Updates all the VRSs of a VSC
     * @param parentId: the VSC ID
     * @param onFinish: the callback called upon ending the update
     */
    override suspend fun updateAll(parentId: String, onFinish: (() -> Unit)?) {
        val await = RetrofitSingleton
            .vrsService()
            ?.getVscVrs(parentId)

        withContext(Dispatchers.IO){
            val completed = await?.await()
            completed?.forEach { dao.save(it) }
            onFinish?.invoke()
        }
    }

    /**
     * Updates all the global VRSs
     * @param onFinish: the callback called upon ending the update
     */
    override suspend fun updateGlobal(onFinish: (() -> Unit)?) {
        val await = RetrofitSingleton
            .vrsService()
            ?.getGlobalVrss()

        withContext(Dispatchers.IO){
            val completed = await?.await()
            completed?.forEach { dao.save(it) }
            onFinish?.invoke()
        }
    }

}