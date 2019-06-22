package pt.isel.vsddashboardapplication.repository

import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.LiveData
import pt.isel.vsddashboardapplication.model.NSPort

/**
 * Repository responsible for getting the information of one port or
 * more related to the NSG
 */
interface PortRepository : LifecycleObserver  {

    suspend fun get(id: String) : LiveData<NSPort>
    suspend fun getForNSGateway(nsgId: String)  : LiveData<List<NSPort>>

    suspend fun update(id: String)
    suspend fun updateAll(id: String): Unit?

}