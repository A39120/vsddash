package pt.isel.vsddashboardapplication.repository.implementation

import pt.isel.vsddashboardapplication.repository.EventRepository
import pt.isel.vsddashboardapplication.repository.services.RetrofitSingleton

class EventRepositoryImpl : EventRepository {

    override suspend fun request() =
            RetrofitSingleton.eventServices()?.getEvent()?.await()

}