package pt.isel.vsddashboardapplication.repository.implementation

import android.util.Log
import androidx.lifecycle.LiveData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import pt.isel.vsddashboardapplication.model.Enterprise
import pt.isel.vsddashboardapplication.repository.EnterpriseRepository
import pt.isel.vsddashboardapplication.repository.dao.EnterpriseDao
import pt.isel.vsddashboardapplication.repository.services.RetrofitServices
import pt.isel.vsddashboardapplication.repository.services.vsd.EnterpriseService
import javax.inject.Inject

class EnterpriseRepositoryImpl @Inject constructor(private val dao: EnterpriseDao)
    : EnterpriseRepository {
    companion object{
        private const val TAG = "REPO/ENTERPRISE"
    }

    private val services: EnterpriseService? by lazy {
        RetrofitServices
            .getInstance()
            .createVsdService(EnterpriseService::class.java)
    }

    override suspend fun get(id: String): LiveData<Enterprise> {
        Log.d(TAG, "Getting enterprise $id")
        val value = dao.load(id)
        if(value.value == null)
            update(id)

        return value
    }

    override suspend fun getAll(parentId: String): LiveData<List<Enterprise>> {
        Log.d(TAG, "Getting list of enterprises of user $parentId")
        val values = dao.loadAll(parentId)

        if(values.value == null && values.value!!.isEmpty())
            updateAll(parentId)

        return values
    }

    override suspend fun update(id: String) {
        Log.d(TAG, "Updating enterprise $id")
        withContext(Dispatchers.IO){
            services?.getEnterprise(id)?.await()?.let { enterprise ->
                dao.save(enterprise)
            }
        }
    }

    override suspend fun updateAll(parentId: String) {
        Log.d(TAG, "Updating enterprises of user $parentId")
        withContext(Dispatchers.IO){
            services?.getEnterprises()?.await()?.forEach { enterprise ->
                enterprise.userId = parentId
                dao.save(enterprise)
            }
        }
    }

}