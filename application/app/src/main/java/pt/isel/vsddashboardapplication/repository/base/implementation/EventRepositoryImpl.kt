package pt.isel.vsddashboardapplication.repository.base.implementation

import pt.isel.vsddashboardapplication.repository.base.EventRepository
import pt.isel.vsddashboardapplication.repository.services.RetrofitSingleton

class EventRepositoryImpl : EventRepository {

    override suspend fun request(uuid: String?) =
            RetrofitSingleton.eventServices()?.getEvent(uuid)

}