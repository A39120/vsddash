package pt.isel.vsddashboardapplication.repository.implementation

import androidx.lifecycle.LiveData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import pt.isel.vsddashboardapplication.communication.services.NSPortServices
import pt.isel.vsddashboardapplication.repository.PortRepository
import pt.isel.vsddashboardapplication.repository.dao.NSPortDao
import pt.isel.vsddashboardapplication.repository.pojo.NSPort

class NSPortRepoImpl(
    private val dao: NSPortDao,
    private val service: NSPortServices
) : PortRepository {

    override fun get(id: String): LiveData<NSPort> =
        dao.load(id)


    override fun getForNSGateway(nsgId: String): LiveData<List<NSPort>> =
        dao.loadForNsg(nsgId)

    override suspend fun update(id: String) = withContext(Dispatchers.IO) {
        val port = service.getPort(id)
        dao.save(port)
    }


    override suspend fun updateAll(id: String) = withContext(Dispatchers.IO) {
        val ports = service.getGatewayPorts(id)
        ports?.forEach { dao.save(it) }
    }

}