package pt.isel.vsddashboardapplication.service

import android.content.Context
import android.util.Log
import androidx.work.*
import kotlinx.coroutines.coroutineScope
import pt.isel.vsddashboardapplication.model.*
import pt.isel.vsddashboardapplication.model.events.Event
import pt.isel.vsddashboardapplication.repository.base.EventRepository
import pt.isel.vsddashboardapplication.repository.base.implementation.EventRepositoryImpl
import pt.isel.vsddashboardapplication.repository.dao.*
import pt.isel.vsddashboardapplication.repository.database.VsdDatabase
import java.lang.Exception
import java.util.concurrent.TimeUnit

/**
 * Service that will do long-polling to the server
 */
class EventWorker(appContext: Context, workParams: WorkerParameters) : CoroutineWorker(appContext, workParams){
    companion object {
        private const val TAG = "WORKER/EVENT"

        /**
         * Enqueues the event work
         */
        fun enqueue() : Operation {
            val constrains = Constraints.Builder()
                .build()

            val workRequest = OneTimeWorkRequestBuilder<EventWorker>()
                .setBackoffCriteria(
                    BackoffPolicy.LINEAR,
                    30,
                    TimeUnit.SECONDS )
                .setConstraints(constrains)
                .build()

            return WorkManager.getInstance().enqueue(workRequest)
        }

    }

    private val dao : EventDao by lazy { VsdDatabase.getInstance().eventsDao() }
    private val eventRepository: EventRepository by lazy { EventRepositoryImpl(dao) }

    /**
     * Will listen to events, if successful it will analyze the event and do work with it, it will
     * retry upon success. It will stop doing work if something went wrong - can't connect to the
     * API for example
     * @return Result.retry(): to retry the job
     *         Result.failure(): if it failed
     */
    override suspend fun doWork(): Result = coroutineScope {
        Log.d(TAG, "Getting event.")
        val uuid = eventRepository.getLastUUID()
        eventRepository.request(uuid)?.run {
            try {
                val events = await()

                dao.deleteAll()
                if (events != null)
                    dao.save(events)

                Log.d(TAG, "Got the events with UUID: $uuid")
                val list = events?.events
                    ?.filterNotNull()

                list?.let { workWithEvent(events.events.filterNotNull()) }
            } catch (ex: Exception) {
                Log.e(TAG, "Exception occurred - ${ex.message}")
                VsdDatabase.getInstance().eventsDao().deleteAll()
            }
        }

        return@coroutineScope Result.retry()
    }

    /**
     * Will do work with the list of events received
     * @param events: list of events
     */
    private fun workWithEvent(events: List<Event>?) {
        Log.d(TAG, "Doing work on event")
        if(events.isNullOrEmpty())
            return

        events.forEach {
            Log.d(TAG, "Analysing event with type ${it.entityType}")

            val entity = it.entityType
            val db = VsdDatabase.getInstance()

            when(entity) {
                "nsgateway" -> {
                    val dao = db.nsgDao()
                    val list = it.entities?.map { obj -> obj.let { map ->
                        val truMap = map as Map<String, Any?>
                        NSGateway.fromMap(truMap)
                    } }
                    apply(dao, it.type, list?.filterNotNull())
                }
                "nsport" -> {
                    val dao = db.nsportDao()
                    val list = it.entities?.map { obj -> obj.let { map ->
                        val truMap = map as Map<String, Any?>
                        NSPort.fromMap(truMap)
                    } }
                    apply(dao, it.type, list?.filterNotNull())
                }
                "alarm" ->  {
                    val dao = db.nsAlarmDao()
                    val list = it.entities?.map { obj -> obj.let { map ->
                        val truMap = map as Map<String, Any?>
                        Alarm.fromMap(truMap)
                    } }
                    apply(dao, it.type, list?.filterNotNull())
                }
                else -> return
            }
        }
    }

    /**
     * Function that will analyse the type of event - CREATE, UPDATE, DELETE
     * @param dao: The Data access object
     * @param type: the type of action the dao will perform
     * @param entity: the entity that will be focused with
     */
    private fun <T> apply(dao: BaseDao<T>, type: String?, entity: List<T>?) {
        entity?.let {
            when (type) {
                "CREATE", "UPDATE" -> it.forEach(dao::save)
                "DELETE" -> it.forEach { ent -> dao.delete(ent) }
                else -> return
            }
        }
    }

}