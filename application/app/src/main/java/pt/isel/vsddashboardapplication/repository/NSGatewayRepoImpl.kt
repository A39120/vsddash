package pt.isel.vsddashboardapplication.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import pt.isel.vsddashboardapplication.repository.pojo.NSGateway
import kotlin.collections.listOf as listOf1

class NSGatewayRepoImpl : NSGatewayRepository {

    override fun get(id: String): LiveData<NSGateway> {
        val ld = MutableLiveData<NSGateway>()
        val gateway = NSGateway(name="NSG1")
        ld.value = gateway
        return ld
    }

    override fun getAll(): LiveData<List<NSGateway>> {
        val ld = MutableLiveData<List<NSGateway>>()
        val nsg1 = NSGateway(name="NSG1")
        val nsg2 = NSGateway(name="NSG2")
        val nsg3 = NSGateway(name="NSG3")

        ld.value = listOf1(nsg1, nsg2, nsg3)
        return ld
    }

}