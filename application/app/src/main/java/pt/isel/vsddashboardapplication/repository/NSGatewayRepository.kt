package pt.isel.vsddashboardapplication.repository

import androidx.lifecycle.LiveData
import pt.isel.vsddashboardapplication.repository.pojo.NSGateway

interface NSGatewayRepository {

    fun get(id: String) : LiveData<NSGateway>

    fun getAll() : LiveData<List<NSGateway>>

}