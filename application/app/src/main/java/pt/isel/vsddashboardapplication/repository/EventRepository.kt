package pt.isel.vsddashboardapplication.repository

import pt.isel.vsddashboardapplication.model.events.Events

interface EventRepository {

    suspend fun request() : List<Events>?

}