package pt.isel.vsddashboardapplication.repository

import androidx.lifecycle.LiveData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext
import pt.isel.vsddashboardapplication.communication.services.NSGatewayService
import pt.isel.vsddashboardapplication.repository.dao.NSGatewayDao
import pt.isel.vsddashboardapplication.repository.pojo.NSGateway
import kotlin.collections.listOf as listOf1

class NSGatewayRepoImpl(
    private val nsgService: NSGatewayService,
    private val dao: NSGatewayDao) : NSGatewayRepository {

    override fun get(id: String): LiveData<NSGateway> {
        runBlocking {
            updateCurrent(id)
        }
        return dao.load(id)
    }

    override fun getAll(enterprise: String): LiveData<List<NSGateway>> {
        runBlocking {
            updateAll(enterprise)
        }
        return dao.loadAll()
    }


    suspend fun updateCurrent(id: String) = withContext(Dispatchers.IO){
        val gateways = nsgService.getGateway(id).await()
        if(gateways != null)
            if( gateways.isNotEmpty() && gateways.size < 2)
                dao.save(nsgateway = gateways[0])
    }

    suspend fun updateAll(enterprise: String) = withContext(Dispatchers.IO) {
        val gateways = nsgService.getGateways(enterprise).await()
        if(gateways != null && gateways.isNotEmpty())
            gateways.forEach { dao.save(it) }
    }

}