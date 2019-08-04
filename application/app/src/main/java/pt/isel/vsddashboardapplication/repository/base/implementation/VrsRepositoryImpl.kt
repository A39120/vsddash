package pt.isel.vsddashboardapplication.repository.base.implementation

import androidx.lifecycle.LiveData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import pt.isel.vsddashboardapplication.model.VRS
import pt.isel.vsddashboardapplication.repository.base.VrsRepository
import pt.isel.vsddashboardapplication.repository.dao.VrsDao
import pt.isel.vsddashboardapplication.repository.services.RetrofitSingleton
import javax.inject.Inject

/**
 * VRS repository for getting VRS;
 */
class VrsRepositoryImpl @Inject constructor(
    private val dao: VrsDao
): VrsRepository {
    private companion object val TAG = "REPO/VRS"

    /**
     * @return the list of all VRSs
     */
    override suspend fun getGlobal(): LiveData<List<VRS>?> {
        val value = dao.loadGlobal()
        if(value.value == null)
            updateGlobal()
        return value
    }

    /**
     * Gets the VRS given an id
     * @param id: The VRS ID
     * @return Live Data containing the VRS
     */
    override fun get(id: String): LiveData<VRS?> {
        val value = dao.load(id)
        return value
    }

    /**
     * Gets all VRS given a VSC
     * @param parentId: the VSC ID
     * @return Live Data containing the list of VRSs
     */
    override fun getAll(parentId: String): LiveData<List<VRS>?> {
        val values = dao.loadForVsc(parentId)
        return values
    }

    /**
     * Updates the VRS
     * @param id: the VRS ID
     * @param onFinish: the callback called upon ending the update
     */
    override suspend fun update(id: String, onFinish: (() -> Unit)?) {
        val await = RetrofitSingleton
            .vrsService()
            ?.getVrs(id)

        withContext(Dispatchers.IO){
            val completed = await?.await()
            completed?.forEach { dao.save(it) }
            onFinish?.invoke()
        }
    }

    /**
     * Updates all the VRSs of a VSC
     * @param parentId: the VSC ID
     * @param onFinish: the callback called upon ending the update
     */
    override suspend fun updateAll(parentId: String, onFinish: (() -> Unit)?) {
        val await = RetrofitSingleton
            .vrsService()
            ?.getVscVrs(parentId)

        withContext(Dispatchers.IO){
            val completed = await?.await()
            completed?.forEach { dao.save(it) }
            onFinish?.invoke()
        }
    }

    /**
     * Updates all the global VRSs
     * @param onFinish: the callback called upon ending the update
     */
    override suspend fun updateGlobal(onFinish: (() -> Unit)?) {
        val await = RetrofitSingleton
            .vrsService()
            ?.getGlobalVrss()

        withContext(Dispatchers.IO){
            val completed = await?.await()
            completed?.forEach { dao.save(it) }
            onFinish?.invoke()
        }
    }

}