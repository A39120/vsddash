package pt.isel.vsddashboardapplication.repository.base.implementation

import android.util.Log
import androidx.lifecycle.LiveData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import pt.isel.vsddashboardapplication.model.Alarm
import pt.isel.vsddashboardapplication.model.NSGInfo
import pt.isel.vsddashboardapplication.repository.base.NSGatewayRepository
import pt.isel.vsddashboardapplication.repository.dao.NSGatewayDao
import pt.isel.vsddashboardapplication.model.NSGateway
import pt.isel.vsddashboardapplication.repository.dao.AlarmDao
import pt.isel.vsddashboardapplication.repository.dao.NSGInfoDao
import pt.isel.vsddashboardapplication.repository.services.RetrofitSingleton
import javax.inject.Inject

/**
 * Implementation of the NSG Repository
 * Makes calls to IO to get one or more NSG
 */
class NSGatewayRepositoryImpl @Inject constructor(
    private val dao: NSGatewayDao,
    private val alarmDao: AlarmDao,
    private val nsgInfoDao: NSGInfoDao
) : NSGatewayRepository {
    companion object{ private const val TAG = "REPO/NSG" }

    override fun getNsgInfo(id: String): LiveData<NSGInfo?> =
        nsgInfoDao.load(id)

    override suspend fun updateNsgInfo(id: String, onFinish: (() -> Unit)?) = withContext(Dispatchers.IO){
        Log.d(TAG, "Updating nsg $id")
        val gateways = RetrofitSingleton
            .nsgService()
            ?.getGatewayInfo(id)
            ?.await()

        if(!gateways.isNullOrEmpty())
            gateways.forEach { nsgInfoDao.save(it) }

        onFinish?.invoke()
        return@withContext
    }


    override fun getAlarms(id: String): LiveData<List<Alarm>?> =
        alarmDao.loadAll(id)


    /**
     * Updates all alarms of NSG
     * @param id: NSG ID
     */
    override suspend fun updateAlarms(id: String, onFinish: (() -> Unit)?)  = withContext(Dispatchers.IO){
        Log.d(TAG, "Updating alarms of NSG $id")
        val alarms = RetrofitSingleton
            .nsgService()
            ?.getGatewayAlarms(id)
            ?.await()

        if(!alarms.isNullOrEmpty())
            alarms.forEach { alarmDao.save(it) }

        onFinish?.invoke()
        return@withContext

    }


    /**
     * Gets the information on one NSG
     * @param id: the id of the NSG
     * @return the liveData container that will getAlarmLiveData the NSG, taken from DAO
     */
    override fun get(id: String): LiveData<NSGateway?> {
        Log.d(TAG, "Getting NSG $id")
        return dao.load(id)
    }

    /**
     * Gets the list of all NSG for a given enterprise
     * @param parentId: the ID of the enterprise
     * @return the livedata with the list of NSG
     */
    override fun getAll(parentId: String): LiveData<List<NSGateway>?> {
        Log.d(TAG, "Getting NSGs of enterprise $parentId")
        return dao.loadAll()
    }


    /**
     * Updates a single NSG
     * @param id: the NSG ID
     */
    override suspend fun update(id: String, onFinish: (() -> Unit)?) = withContext(Dispatchers.IO){
        Log.d(TAG, "Updating nsg $id")
        val gateways = RetrofitSingleton
            .nsgService()
            ?.getGateway(id)
            ?.await()

        if(gateways != null)
            if( gateways.isNotEmpty() && gateways.size < 2)
                dao.save(nsgateway = gateways[0])

        onFinish?.invoke()
        return@withContext
    }

    /**
     * Updates a list of NSGs, given an enterprise
     * @param parentId: the enterprise ID
     */
    override suspend fun updateAll(parentId: String, onFinish: (() -> Unit)?) = withContext(Dispatchers.IO) {
        Log.d(TAG, "Updating list of NSGs of enterprise $parentId")
        val service = RetrofitSingleton.nsgService()
        val gateways = service
            ?.getGateways(parentId)
            ?.await()

        if(gateways != null && gateways.isNotEmpty())
            gateways.forEach { dao.save(it) }

        Log.d(TAG, "Updated all NSGs of enterprise $parentId")
        onFinish?.invoke()
        return@withContext
    }

}