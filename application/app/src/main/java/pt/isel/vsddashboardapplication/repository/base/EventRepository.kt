package pt.isel.vsddashboardapplication.repository.base

import kotlinx.coroutines.Deferred
import pt.isel.vsddashboardapplication.model.events.Events

interface EventRepository {

    suspend fun request(uuid: String?) : Deferred<Events?>?

    fun getLastUUID() : String?

}