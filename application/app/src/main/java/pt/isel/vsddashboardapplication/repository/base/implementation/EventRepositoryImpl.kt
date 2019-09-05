package pt.isel.vsddashboardapplication.repository.base.implementation

import pt.isel.vsddashboardapplication.repository.base.EventRepository
import pt.isel.vsddashboardapplication.repository.dao.EventDao
import pt.isel.vsddashboardapplication.repository.services.RetrofitSingleton
import javax.inject.Inject

class EventRepositoryImpl @Inject constructor(
    private val dao: EventDao
): EventRepository {

    override fun getLastUUID(): String? =
        dao.getEvents().maxBy { it.timestamp ?: 0}?.uuid

    override suspend fun request(uuid: String?) =
            RetrofitSingleton.eventServices()?.getEvent(uuid)

}