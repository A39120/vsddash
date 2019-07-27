package pt.isel.vsddashboardapplication.repository.base.implementation

import android.util.Log
import androidx.lifecycle.LiveData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import pt.isel.vsddashboardapplication.model.APM
import pt.isel.vsddashboardapplication.repository.base.ApmRepository
import pt.isel.vsddashboardapplication.repository.dao.ApmDao
import pt.isel.vsddashboardapplication.repository.services.RetrofitSingleton
import javax.inject.Inject

class ApmRepositoryImpl @Inject constructor(
    private val dao: ApmDao
): ApmRepository {
    companion object {
        const val TAG = "REPO/APM"
    }

    override suspend fun get(id: String): LiveData<APM> {
        Log.d(TAG, "Getting APM $id")
        val value = dao.load(id)
        if(value.value == null)
            update(id)

        return value
    }

    override suspend fun getAll(parentId: String): LiveData<List<APM>> {
        Log.d(TAG, "Getting APMs of enterprise ($parentId)")
        val value = dao.loadAll(parentId)
        if(value.value == null)
            updateAll(parentId)

        return value

    }

    override suspend fun update(id: String, onFinish: (() -> Unit)?) {
        Log.d(TAG, "Updating APM ($id)")
        withContext(Dispatchers.IO) {
            RetrofitSingleton
                .apmServices()
                ?.get(id)
                ?.await()
                ?.let { apm -> apm.forEach { dao.save(it) } }

            onFinish?.invoke()
            return@withContext
        }
    }

    override suspend fun updateAll(parentId: String, onFinish: (() -> Unit)?) {
        Log.d(TAG, "Updating APMs of enterprise $parentId")
        withContext(Dispatchers.IO) {
            RetrofitSingleton
                .apmServices()
                ?.getApms(parentId)
                ?.await()
                ?.forEach { apm -> dao.save(apm) }


            onFinish?.invoke()
            return@withContext
        }
    }

}