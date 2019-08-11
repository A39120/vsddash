package pt.isel.vsddashboardapplication.repository.base.implementation

import android.util.Log
import androidx.lifecycle.LiveData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import pt.isel.vsddashboardapplication.model.Alarm
import pt.isel.vsddashboardapplication.model.VSC
import pt.isel.vsddashboardapplication.repository.base.VscRepository
import pt.isel.vsddashboardapplication.repository.dao.AlarmDao
import pt.isel.vsddashboardapplication.repository.dao.VscDao
import pt.isel.vsddashboardapplication.repository.services.RetrofitSingleton
import javax.inject.Inject

/**
 * VSC repository for getting VSC;
 */
class VscRepositoryImpl @Inject constructor(
    private val dao: VscDao,
    private val alarmDao: AlarmDao
): VscRepository {

    override fun getAlarms(parent: String): LiveData<List<Alarm>?> =
        alarmDao.loadAll(parent)


    override suspend fun updateAlarms(parent: String, onFinish: (() -> Unit)?) {
        val deferred = RetrofitSingleton
            .vscService()
            ?.getVcsAlarms(parent)

        withContext(Dispatchers.IO){
            val result = deferred?.await()
            Log.d(TAG, "Got ${result?.size?:0} alarms")
            result?.forEach { alarm -> alarmDao.save(alarm) }

            onFinish?.invoke()
        }
    }

    private companion object val TAG = "REPO/VSC"

    /**
     * Gets the VSC
     * @param id: VSC ID
     * @return Live Data containing the VSC
     */
    override fun get(id: String): LiveData<VSC?> {
        return dao.load(id)
    }

    /**
     * Gets all the VSCs
     * @param parentId: the VSP ID
     * @return Live Data containing the list of VSCs
     */
    override fun getAll(parentId: String): LiveData<List<VSC>?> {
        return dao.loadForVsp(parentId)
    }

    /**
     * Updates the room registry
     * @param id: the VSC id
     * @param onFinish: callback function called on finish update
     */
    override suspend fun update(id: String, onFinish: (() -> Unit)?) {
        val await = RetrofitSingleton
            .vscService()
            ?.getVcs(id)

        withContext(Dispatchers.IO){
            val completed = await?.await()
            completed?.forEach { dao.save(it) }
            onFinish?.invoke()
        }
    }


    /**
     * Updates all the registries of Room
     * @param parentId: the VSP ID
     * @param onFinish: callback called upon ending the update
     */
    override suspend fun updateAll(parentId: String, onFinish: (() -> Unit)?) {
        val await = RetrofitSingleton
            .vscService()
            ?.getVcsc(parentId)

        withContext(Dispatchers.IO){
            val completed = await?.await()
            completed?.forEach { dao.save(it) }
            onFinish?.invoke()
        }
    }

}
