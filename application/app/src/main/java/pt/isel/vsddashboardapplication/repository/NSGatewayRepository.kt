package pt.isel.vsddashboardapplication.repository

import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.LiveData
import pt.isel.vsddashboardapplication.model.NSGateway

interface NSGatewayRepository : LifecycleObserver {

    suspend fun get(id: String) : LiveData<NSGateway>
    suspend fun getAll(enterprise: String) : LiveData<List<NSGateway>>

    suspend fun update(id: String)
    suspend fun updateAll(enterprise: String)
}