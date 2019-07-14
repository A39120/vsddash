package pt.isel.vsddashboardapplication.repository

import androidx.lifecycle.LiveData
import pt.isel.vsddashboardapplication.model.statistics.DpiProbestats

interface DpiProbestatsRepository {

    suspend fun getInbound(port: String, nsg: String, apm:String?, start: Long, end: Long) : LiveData<List<DpiProbestats>>

    suspend fun getOutbound(port: String, nsg: String, apm: String?, start: Long, end: Long) : LiveData<List<DpiProbestats>>

    suspend fun updateInbound(port: String, nsg: String, apm: String?, start: Long, end: Long, onFinished: ((Unit) -> Unit)? = null)

    suspend fun updateOutbound(port: String, nsg: String, apm:String?, start: Long, end: Long, onFinished: ((Unit) -> Unit)? = null)

}