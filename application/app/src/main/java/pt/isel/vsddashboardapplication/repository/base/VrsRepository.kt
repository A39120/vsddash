package pt.isel.vsddashboardapplication.repository.base

import androidx.lifecycle.LiveData
import pt.isel.vsddashboardapplication.model.Alarm
import pt.isel.vsddashboardapplication.model.VPort
import pt.isel.vsddashboardapplication.model.VRS

interface VrsRepository : IBaseRepository<VRS> {

    fun getGlobal() : LiveData<List<VRS>?>

    suspend fun updateGlobal(onFinish: (() -> Unit)? = null)

    fun getAlarms(parent: String) : LiveData<List<Alarm>?>

    suspend fun updateAlarms(parent: String, onFinish: (() -> Unit)? = null)

    fun getVports(parent: String) : LiveData<List<VPort>?>

    suspend fun updateVports(parent: String, onFinish: (() -> Unit)? = null)

    suspend fun updateForVscChild(id: String, vsc: String?, onFinish: (() -> Unit)? = null)
}