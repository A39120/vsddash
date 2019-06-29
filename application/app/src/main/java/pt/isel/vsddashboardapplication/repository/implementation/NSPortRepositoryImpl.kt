package pt.isel.vsddashboardapplication.repository.implementation

import androidx.lifecycle.LiveData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import pt.isel.vsddashboardapplication.communication.services.vsd.NSPortServices
import pt.isel.vsddashboardapplication.communication.services.RetrofitServices
import pt.isel.vsddashboardapplication.repository.PortRepository
import pt.isel.vsddashboardapplication.repository.dao.NSPortDao
import pt.isel.vsddashboardapplication.model.NSPort
import javax.inject.Inject

/**
 * The implementation of a NSPortRepository
 */
class NSPortRepositoryImpl @Inject constructor(
    private val dao: NSPortDao
) : PortRepository {

    /**
     * Service to get the ports
     */
    private val service: NSPortServices? by lazy {
        RetrofitServices
            .getInstance()
            .createVsdService(NSPortServices::class.java)
    }

    /**
     * Gets the port from the DB/Network
     * @param id: the ID of a Port
     * @return the LiveData of a NSPort
     */
    override suspend fun get(id: String): LiveData<NSPort> {
        val port = dao.load(id)

        if (port.value == null)
            this.update(id)

        return port
    }


    /**
     * Gets all the ports of a NSG
     * @param parentId: The ID of a existing NSG
     */
    override suspend fun getAll(parentId: String): LiveData<List<NSPort>> {
        val ports = dao.loadForNsg(parentId)
        if (ports.value == null)
            this.updateAll(parentId)
        return ports
    }

    /**
     * Updates DB by collecting if possible the information from a network call
     * @param id: the ID of a existing port
     */
    override suspend fun update(id: String) {
        withContext(Dispatchers.IO) {
            val port = service?.getPort(id)?.await()
            port?.run { dao.save(port) }
        }
    }


    /**
     * Updates all the ports of a determined NSG according to the network service
     * if possible.
     *
     * @param parentId: the ID of a NSG
     */
    override suspend fun updateAll(parentId: String) {
        withContext(Dispatchers.IO) {
            val ports = service?.getGatewayPorts(parentId)?.await()
            ports?.forEach { dao.save(it) }
        }
    }

}