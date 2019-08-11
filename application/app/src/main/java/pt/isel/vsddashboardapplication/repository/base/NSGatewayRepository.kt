package pt.isel.vsddashboardapplication.repository.base

import androidx.lifecycle.LiveData
import pt.isel.vsddashboardapplication.model.NSGInfo
import pt.isel.vsddashboardapplication.model.NSGateway

interface NSGatewayRepository : IBaseRepository<NSGateway>, AlarmParentRepository {

    fun getNsgInfo(id: String) : LiveData<NSGInfo?>

    suspend fun updateNsgInfo(id: String, onFinish: (() -> Unit)? = null)
}
