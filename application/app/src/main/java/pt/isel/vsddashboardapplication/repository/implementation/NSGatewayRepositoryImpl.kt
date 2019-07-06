package pt.isel.vsddashboardapplication.repository.implementation

import android.util.Log
import androidx.lifecycle.LiveData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import pt.isel.vsddashboardapplication.repository.services.vsd.NSGatewayService
import pt.isel.vsddashboardapplication.repository.services.RetrofitServices
import pt.isel.vsddashboardapplication.repository.NSGatewayRepository
import pt.isel.vsddashboardapplication.repository.dao.NSGatewayDao
import pt.isel.vsddashboardapplication.model.NSGateway
import javax.inject.Inject

/**
 * Implementation of the NSG Repository
 * Makes calls to IO to get one or more NSG
 */
class NSGatewayRepositoryImpl @Inject constructor(private val dao: NSGatewayDao) : NSGatewayRepository {
    companion object{
        private const val TAG = "REPO/NSG"
    }

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
     * @return the liveData container that will observe the NSG, taken from DAO
     */
    override suspend fun get(id: String): LiveData<NSGateway> {
        Log.d(TAG, "Getting NSG $id")
        val value = dao.load(id)
        if(value.value == null)
            update(id)

        return dao.load(id)
    }

    /**
     * Gets the list of all NSG for a given enterprise
     * @param parentId: the ID of the enterprise
     * @return the livedata with the list of NSG
     */
    override suspend fun getAll(parentId: String): LiveData<List<NSGateway>> {
        Log.d(TAG, "Getting NSGs of enterprise $parentId")
        val value = dao.loadAll()
        if(value.value == null)
            updateAll(parentId)

        return dao.loadAll()
    }


    /**
     * Updates a single NSG
     * @param id: the NSG ID
     */
    override suspend fun update(id: String) = withContext(Dispatchers.IO){
        Log.d(TAG, "Updating nsg $id")
        val gateways = nsgService?.getGateway(id)?.await()
        if(gateways != null)
            if( gateways.isNotEmpty() && gateways.size < 2)
                dao.save(nsgateway = gateways[0])
    }

    /**
     * Updates a list of NSGs, given an enterprise
     * @param parentId: the enterprise ID
     */
    override suspend fun updateAll(parentId: String) = withContext(Dispatchers.IO) {
        Log.d(TAG, "Updating list of NSGs of enterprise $parentId")
        val gateways = nsgService?.getGateways(parentId)?.await()
        if(gateways != null && gateways.isNotEmpty())
            gateways.forEach { dao.save(it) }
    }

}