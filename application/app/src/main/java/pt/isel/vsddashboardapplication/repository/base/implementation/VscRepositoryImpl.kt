package pt.isel.vsddashboardapplication.repository.base.implementation

import androidx.lifecycle.LiveData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import pt.isel.vsddashboardapplication.model.VSC
import pt.isel.vsddashboardapplication.repository.base.VscRepository
import pt.isel.vsddashboardapplication.repository.dao.VscDao
import pt.isel.vsddashboardapplication.repository.services.RetrofitSingleton
import javax.inject.Inject

/**
 * VSC repository for getting VSC;
 */
class VscRepositoryImpl @Inject constructor(
    private val dao: VscDao
): VscRepository {
    private companion object val TAG = "REPO/VSC"

    /**
     * Gets the VSC
     * @param id: VSC ID
     * @return Live Data containing the VSC
     */
    override fun get(id: String): LiveData<VSC?> {
        val value = dao.load(id)
        return value
    }

    /**
     * Gets all the VSCs
     * @param parentId: the VSP ID
     * @return Live Data containing the list of VSCs
     */
    override fun getAll(parentId: String): LiveData<List<VSC>?> {
        val values = dao.loadForVsp(parentId)
        return values
    }

    /**
     * Updates the room registry
     * @param id: the VSC id
     * @param onFinish: callback function called on finish update
     */
    override suspend fun update(id: String, onFinish: (() -> Unit)?) {
        val await = RetrofitSingleton
            .vscService()
            ?.getVcs(id)

        withContext(Dispatchers.IO){
            val completed = await?.await()
            completed?.forEach { dao.save(it) }
            onFinish?.invoke()
        }
    }


    /**
     * Updates all the registries of Room
     * @param parentId: the VSP ID
     * @param onFinish: callback called upon ending the update
     */
    override suspend fun updateAll(parentId: String, onFinish: (() -> Unit)?) {
        val await = RetrofitSingleton
            .vscService()
            ?.getVcsc(parentId)

        withContext(Dispatchers.IO){
            val completed = await?.await()
            completed?.forEach { dao.save(it) }
            onFinish?.invoke()
        }
    }

}
