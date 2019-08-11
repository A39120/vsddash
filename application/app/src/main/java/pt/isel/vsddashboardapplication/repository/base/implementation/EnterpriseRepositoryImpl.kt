package pt.isel.vsddashboardapplication.repository.base.implementation

import android.util.Log
import androidx.lifecycle.LiveData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import pt.isel.vsddashboardapplication.model.Enterprise
import pt.isel.vsddashboardapplication.repository.base.EnterpriseRepository
import pt.isel.vsddashboardapplication.repository.dao.EnterpriseDao
import pt.isel.vsddashboardapplication.repository.services.RetrofitSingleton
import javax.inject.Inject

class EnterpriseRepositoryImpl @Inject constructor(private val dao: EnterpriseDao)
    : EnterpriseRepository {
    companion object{ private const val TAG = "REPO/ENTERPRISE" }

    private lateinit var user: String
    private lateinit var vsd: String
    private lateinit var organization: String

    override fun setup(user: String, organization: String, vsd: String) {
        this.user = user
        this.vsd = vsd
        this.organization = organization
    }


    override fun get(id: String): LiveData<Enterprise?> {
        Log.d(TAG, "Getting enterprise $id")
        return dao.load(id)
    }

    override fun getAll(parentId: String): LiveData<List<Enterprise>?> {
        Log.d(TAG, "Getting list of enterprises of user $parentId")
        return dao.loadAll(user, organization, vsd)
    }

    override suspend fun update(id: String, onFinish: (() -> Unit)?) {
        Log.d(TAG, "Updating enterprise $id")
        withContext(Dispatchers.IO){
            RetrofitSingleton
                .enterpriseServices()
                ?.getEnterprise(id)
                ?.await()
                ?.let { enterprise ->
                    Log.d(TAG, "Saving enterprise with name ${enterprise.name}")
                    enterprise.user = this@EnterpriseRepositoryImpl.user
                    enterprise.vsd = this@EnterpriseRepositoryImpl.vsd
                    enterprise.organization = this@EnterpriseRepositoryImpl.organization
                    dao.save(enterprise)
                }


            onFinish?.invoke()
            return@withContext
        }
    }

    override suspend fun updateAll(parentId: String, onFinish: (() -> Unit)?) {
        Log.d(TAG, "Updating enterprises of the user $parentId")
        withContext(Dispatchers.IO){
            RetrofitSingleton
                .enterpriseServices()
                ?.getEnterprises()
                ?.await()
                ?.forEach { enterprise ->
                    Log.d(TAG, "Saving enterprise with name ${enterprise.name}")
                    enterprise.user = this@EnterpriseRepositoryImpl.user
                    enterprise.vsd = this@EnterpriseRepositoryImpl.vsd
                    enterprise.organization = this@EnterpriseRepositoryImpl.organization

                    dao.save(enterprise)
                }

            onFinish?.invoke()
            return@withContext
        }
    }

}