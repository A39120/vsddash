package pt.isel.vsddashboardapplication.repository.base.implementation

import androidx.lifecycle.LiveData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import pt.isel.vsddashboardapplication.model.NSGInfo
import pt.isel.vsddashboardapplication.repository.base.NSGinfoRepository
import pt.isel.vsddashboardapplication.repository.dao.NSGInfoDao
import pt.isel.vsddashboardapplication.repository.services.RetrofitSingleton
import javax.inject.Inject

class NSGInfoRepositoryImpl @Inject constructor(private val dao: NSGInfoDao) :
    NSGinfoRepository {

    override suspend fun get(id: String): LiveData<NSGInfo> {
        val liveData = dao.load(id)

        if(liveData.value == null)
            update(id)

        return liveData
    }

    override suspend fun getAll(parentId: String): LiveData<List<NSGInfo>> {
        throw UnsupportedOperationException()
    }

    override suspend fun update(id: String, onFinish: (() -> Unit)?) {
        withContext(Dispatchers.IO){
            val gateways = RetrofitSingleton
                .nsgService()
                ?.getGatewayInfo(id)
                ?.await()

            if(gateways != null)
                if( gateways.isNotEmpty() && gateways.size < 2)
                    dao.save(nsgateway = gateways[0])

            onFinish?.invoke()
            return@withContext
        }
    }

    override suspend fun updateAll(parentId: String, onFinish: (() -> Unit)?) {
        throw UnsupportedOperationException()
    }
}