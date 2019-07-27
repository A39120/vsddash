package pt.isel.vsddashboardapplication.repository.base

import androidx.lifecycle.LiveData
import kotlinx.coroutines.Deferred
import pt.isel.vsddashboardapplication.model.VRS

interface VrsRepository : IBaseRepository<VRS> {

    suspend fun getGlobal() : LiveData<List<VRS>>

    suspend fun updateGlobal(onFinish: (() -> Unit)? = null)
}