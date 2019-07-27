package pt.isel.vsddashboardapplication.repository.base

import androidx.lifecycle.LiveData
import pt.isel.vsddashboardapplication.model.statistics.DpiProbestats

interface DpiProbestatsRepository {

    fun getInbound(port: String, nsg: String, apm:String?, start: Long?, end: Long?) : LiveData<List<DpiProbestats>>

    fun getOutbound(port: String, nsg: String, apm: String?, start: Long?, end: Long?) : LiveData<List<DpiProbestats>>

    suspend fun updateInbound(port: String, nsg: String, apm: String?, start: Long?, end: Long?, onFinished: ((Unit) -> Unit)? = null)

    suspend fun updateOutbound(port: String, nsg: String, apm:String?, start: Long?, end: Long?, onFinished: ((Unit) -> Unit)? = null)

}