package pt.isel.vsddashboardapplication.repository.base.implementation

import androidx.lifecycle.LiveData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import pt.isel.vsddashboardapplication.model.VRS
import pt.isel.vsddashboardapplication.repository.base.VrsRepository
import pt.isel.vsddashboardapplication.repository.dao.VrsDao
import pt.isel.vsddashboardapplication.repository.services.RetrofitSingleton
import javax.inject.Inject

class VrsRepositoryImplementation @Inject constructor(
    private val dao: VrsDao
): VrsRepository {

    override suspend fun getGlobal(): LiveData<List<VRS>> {
        val value = dao.loadGlobal()
        if(value.value == null)
            updateGlobal()

        return value
    }

    private companion object val TAG = "REPO/VRS"

    override suspend fun get(id: String): LiveData<VRS> {
        val value = dao.load(id)
        if(value.value == null)
            update(id)
        return value
    }

    override suspend fun getAll(parentId: String): LiveData<List<VRS>> {
        val values = dao.loadForVsc(parentId)
        if(values.value == null || values.value!!.isEmpty())
            updateAll(parentId)
        return values
    }

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