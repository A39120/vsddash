package pt.isel.vsddashboardapplication.repository.base.implementation

import androidx.lifecycle.LiveData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import pt.isel.vsddashboardapplication.model.Health
import pt.isel.vsddashboardapplication.repository.base.HealthRepository
import pt.isel.vsddashboardapplication.repository.dao.HealthDao
import pt.isel.vsddashboardapplication.repository.services.RetrofitSingleton
import javax.inject.Inject

class HealthRepositoryImpl @Inject constructor(
    private val healthDao: HealthDao
): HealthRepository {

    override fun get(): LiveData<List<Health>?> {
        return healthDao.load()
    }

    override suspend fun update(onSuccess: (() -> Unit)?) {
        val health = RetrofitSingleton
            .healthService()
            ?.getHealth()

        val elastic = RetrofitSingleton
            .healthService()
            ?.getHealth("elastic")

        try {
            withContext(Dispatchers.IO) {
                health
                    ?.await()
                    ?.forEach { vsd ->
                        vsd.name = "VSD"
                        healthDao.save(vsd)
                    }

                elastic?.await()
                    ?.forEach { elastic ->
                        elastic.name = "Elastic Search"
                        healthDao.save(elastic)
                    }
            }
        } finally {
            onSuccess?.invoke()
        }
    }

}