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

    override fun get(): LiveData<Health?> {
        return healthDao.load()
    }

    override suspend fun update() {
        val health = RetrofitSingleton
            .healthService()
            ?.getHealth()
        withContext(Dispatchers.IO){
            health
                ?.await()
                ?.forEach { healthDao.save(it) }
        }
    }

}