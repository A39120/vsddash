package pt.isel.vsddashboardapplication.repository.implementation

import android.util.Log
import androidx.lifecycle.LiveData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import pt.isel.vsddashboardapplication.repository.services.vsd.NSPortServices
import pt.isel.vsddashboardapplication.repository.services.RetrofitServices
import pt.isel.vsddashboardapplication.repository.PortRepository
import pt.isel.vsddashboardapplication.repository.dao.NSPortDao
import pt.isel.vsddashboardapplication.model.NSPort
import pt.isel.vsddashboardapplication.repository.services.RetrofitSingleton
import javax.inject.Inject

/**
 * The implementation of a NSPortRepository
 */
class NSPortRepositoryImpl @Inject constructor(
    private val dao: NSPortDao
) : PortRepository {
    companion object{
        private const val TAG = "REPO/NSPORT"
    }


    /**
     * Gets the port from the DB/Network
     * @param id: the ID of a Port
     * @return the LiveData of a NSPort
     */
    override suspend fun get(id: String): LiveData<NSPort> {
        Log.d(TAG, "Getting NSPort $id")
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
        Log.d(TAG, "Getting NSPort of NSG $parentId")
        val ports = dao.loadForNsg(parentId)
        if (ports.value == null)
            this.updateAll(parentId)
        return ports
    }

    /**
     * Updates DB by collecting if possible the information from a network call
     * @param id: the ID of a existing port
     */
    override suspend fun update(id: String, onFinish: (() -> Unit)?) {
        Log.d(TAG, "Updating NSPort $id")
        withContext(Dispatchers.IO) {
            val port = RetrofitSingleton
                .nsportServices()
                ?.getPort(id)
                ?.await()

            port?.forEach { dao.save(it) }
            onFinish?.invoke()
            return@withContext
        }
    }


    /**
     * Updates all the ports of a determined NSG according to the network service
     * if possible.
     *
     * @param parentId: the ID of a NSG
     */
    override suspend fun updateAll(parentId: String, onFinish: (() -> Unit)?) {
        Log.d(TAG, "Updating NSPorts of NSG $parentId")
        withContext(Dispatchers.IO) {
            val ports = RetrofitSingleton
                .nsportServices()
                ?.getGatewayPorts(parentId)
                ?.await()

            ports?.forEach { dao.save(it) }
            onFinish?.invoke()
            return@withContext
        }
    }

}