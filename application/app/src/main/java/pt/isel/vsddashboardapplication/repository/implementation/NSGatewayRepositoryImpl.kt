package pt.isel.vsddashboardapplication.repository.implementation

import androidx.lifecycle.LiveData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import pt.isel.vsddashboardapplication.communication.services.vsd.NSGatewayService
import pt.isel.vsddashboardapplication.communication.services.RetrofitServices
import pt.isel.vsddashboardapplication.repository.NSGatewayRepository
import pt.isel.vsddashboardapplication.repository.dao.NSGatewayDao
import pt.isel.vsddashboardapplication.model.NSGateway
import javax.inject.Inject

/**
 * Implementation of the NSG Repository
 * Makes calls to IO to get one or more NSG
 */
class NSGatewayRepositoryImpl @Inject constructor(private val dao: NSGatewayDao) : NSGatewayRepository {

    /**
     * Service used for network calls
     */
    private val nsgService: NSGatewayService? by lazy {
        RetrofitServices
            .getInstance()
            .createVsdService(NSGatewayService::class.java)
    }

    /**
     * Gets the information on one NSG
     * @param id: the id of the NSG
     * @return the livedata container that will observe the NSG, taken from DAO
     */
    override suspend fun get(id: String): LiveData<NSGateway> {
        val value = dao.load(id)
        if(value.value == null)
            update(id)
        return dao.load(id)
    }

    /**
     * Gets the list of all NSG for a given enterprise
     * @param enterprise: the ID of the enterprise
     * @return the livedata with the list of NSG
     */
    override suspend fun getAll(enterprise: String): LiveData<List<NSGateway>> {
        val value = dao.loadAll()
        if(value.value == null)
            updateAll(enterprise)

        return dao.loadAll()
    }


    /**
     * Updates a single NSG
     * @param id: the NSG ID
     */
    override suspend fun update(id: String) = withContext(Dispatchers.IO){
        val gateways = nsgService?.getGateway(id)?.await()
        if(gateways != null)
            if( gateways.isNotEmpty() && gateways.size < 2)
                dao.save(nsgateway = gateways[0])
    }

    /**
     * Updates a list of NSGs, given an enterprise
     * @param enterprise: the enterprise ID
     */
    override suspend fun updateAll(enterprise: String) = withContext(Dispatchers.IO) {
        val gateways = nsgService?.getGateways(enterprise)?.await()
        if(gateways != null && gateways.isNotEmpty())
            gateways.forEach { dao.save(it) }
    }

}