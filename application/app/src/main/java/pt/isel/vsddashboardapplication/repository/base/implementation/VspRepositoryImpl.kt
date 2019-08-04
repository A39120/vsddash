package pt.isel.vsddashboardapplication.repository.base.implementation

import androidx.lifecycle.LiveData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import pt.isel.vsddashboardapplication.model.VSP
import pt.isel.vsddashboardapplication.repository.base.VspRepository
import pt.isel.vsddashboardapplication.repository.dao.VspDao
import pt.isel.vsddashboardapplication.repository.services.RetrofitSingleton
import javax.inject.Inject

/**
 * VSP repository for getting VSP;
 * @param dao: the data access object of VSP
 */
class VspRepositoryImpl @Inject constructor(
    private val dao: VspDao
): VspRepository {
    private companion object val TAG = "REPO/VSP"

    /**
     * Gets the VSP
     * @return live data containing the list of VSP
     */
    override fun get(): LiveData<List<VSP>?> {
        return dao.loadAll()
    }

    /**
     * Updates the VSP
     * @param onFinish: function called upon the update finished
     */
    override suspend fun update(onFinish: (() -> Unit)?) {
        val await = RetrofitSingleton
            .vspService()
            ?.getVsps()

        withContext(Dispatchers.IO){
            val completed = await?.await()
            completed?.forEach { dao.save(it) }
            onFinish?.invoke()
        }
    }

}
