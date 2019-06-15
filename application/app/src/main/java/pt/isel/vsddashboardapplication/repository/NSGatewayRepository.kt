package pt.isel.vsddashboardapplication.repository

import androidx.lifecycle.LiveData
import pt.isel.vsddashboardapplication.repository.pojo.NSGateway

interface NSGatewayRepository {

    fun get(id: String) : LiveData<NSGateway>

    fun getAll(enterprise: String) : LiveData<List<NSGateway>>

    suspend fun update(id: String)

    suspend fun updateAll(enterprise: String)

}