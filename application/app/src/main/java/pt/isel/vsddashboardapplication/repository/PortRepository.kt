package pt.isel.vsddashboardapplication.repository

import androidx.lifecycle.LiveData
import pt.isel.vsddashboardapplication.repository.pojo.NSPort

interface PortRepository {

    fun get(id: String) : LiveData<NSPort>

    fun getForNSGateway(nsgId: String)  : LiveData<List<NSPort>>

    suspend fun update(id: String)

    suspend fun updateAll(id: String): Unit?

}