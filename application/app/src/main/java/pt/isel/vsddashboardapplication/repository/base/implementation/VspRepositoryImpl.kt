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
 */
class VspRepositoryImpl @Inject constructor(
    private val dao: VspDao
): VspRepository {
    private companion object val TAG = "REPO/VSP"

    override suspend fun get(): LiveData<List<VSP>> {
        val value = dao.loadAll()
        if(value.value == null)
            update()

        return value
    }

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