package pt.isel.vsddashboardapplication.repository.base

import pt.isel.vsddashboardapplication.model.events.Events

interface EventRepository {

    suspend fun request() : List<Events>?

}