package pt.isel.vsddashboardapplication.repository.base

import androidx.lifecycle.LiveData
import pt.isel.vsddashboardapplication.model.statistics.Sysmon

interface SysmonRepository {

    suspend fun update(id: String, from: Long?, to: Long?)

    fun get(id: String, from: Long?, to: Long?) : LiveData<List<Sysmon>>

}