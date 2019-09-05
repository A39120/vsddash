package pt.isel.vsddashboardapplication.repository.base.implementation

import android.util.Log
import androidx.lifecycle.LiveData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import pt.isel.vsddashboardapplication.model.Alarm
import pt.isel.vsddashboardapplication.model.VPort
import pt.isel.vsddashboardapplication.repository.base.VPortRepository
import pt.isel.vsddashboardapplication.repository.dao.AlarmDao
import pt.isel.vsddashboardapplication.repository.dao.VPortDao
import pt.isel.vsddashboardapplication.repository.services.RetrofitSingleton
import javax.inject.Inject

class VPortRepositoryImpl @Inject constructor(
    private val dao: VPortDao,
    private val alarmDao: AlarmDao
) : VPortRepository {
    private companion object val TAG = "REPO/VPORT"

    override fun getAlarms(id: String): LiveData<List<Alarm>?> {
        Log.d(TAG, "Getting alarms for VPORT $id")
        return alarmDao.loadAll(id)
    }

    override suspend fun updateAlarms(id: String, onFinish: (() -> Unit)?) {
        Log.d(TAG, "Updating alarms for VPort $id")
        val def = RetrofitSingleton.vportServices()
            ?.getPortAlarms(id)

        withContext(Dispatchers.IO){
            try {
                def?.await()?.forEach {
                    alarmDao.save(it)
                }
            } finally {
                onFinish?.invoke()
            }
        }
    }

    override fun get(id: String): LiveData<VPort?> {
        Log.d(TAG, "Getting VPort $id")
        return dao.load(id)
    }

    override fun getAll(parentId: String): LiveData<List<VPort>?> {
        Log.d(TAG, "Getting all from VRS $parentId")
        return dao.loadAll(parentId)
    }

    override suspend fun update(id: String, onFinish: (() -> Unit)?) {
        Log.d(TAG, "Updating VPort $id")
        val def = RetrofitSingleton.vportServices()
            ?.getPort(id)

        withContext(Dispatchers.IO){
            try {
                def?.await()?.forEach {
                    dao.save(it)
                }
            } finally {
                onFinish?.invoke()
            }
        }
    }

    override suspend fun updateAll(parentId: String, onFinish: (() -> Unit)?) {
        Log.d(TAG, "Updating VPort $parentId")
        val def = RetrofitSingleton.vportServices()
            ?.getFromVrss(parentId)

        withContext(Dispatchers.IO){
            try {
                def?.await()?.forEach { dao.save(it) }
            } finally {
                onFinish?.invoke()
            }
        }
    }

}