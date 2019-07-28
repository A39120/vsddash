package pt.isel.vsddashboardapplication.service

import android.content.Context
import android.util.Log
import androidx.work.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import pt.isel.vsddashboardapplication.model.events.Event
import pt.isel.vsddashboardapplication.repository.base.EventRepository
import pt.isel.vsddashboardapplication.repository.base.implementation.EventRepositoryImpl

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
                .setRequiresDeviceIdle(false)
                .build()

            val workRequest = OneTimeWorkRequestBuilder<EventWorker>()
                .setConstraints(constrains)
                .build()

            return WorkManager.getInstance().enqueue(workRequest)
        }

    }

    private val eventRepository: EventRepository by lazy { EventRepositoryImpl() }

    private var uuid: String? = null

    /**
     * Will listen to events, if successful it will analyze the event and do work with it, it will
     * retry upon success. It will stop doing work if something went wrong - can't connect to the
     * API for example
     * @return Result.retry(): to retry the job
     *         Result.failure(): if it failed
     */
    override suspend fun doWork(): Result {
        Log.d(TAG, "Getting event.")
        eventRepository.request(uuid)?.run {
            withContext(Dispatchers.IO) {
                val events = await()

                uuid = events?.uuid

                Log.d(TAG, "Got the events with UUID: $uuid")
                val list = events?.events
                    ?.filterNotNull()

                list?.let { workWithEvent(it) }
            }
        }

        return Result.retry()
    }

    /**
     * Will do work with the list of events received
     * @param events:
     */
    private fun workWithEvent(events: List<Event>?) {
        Log.d(TAG, "Doing work on event")
        if(events == null || events.isEmpty())
            return

        events.forEach {
            Log.d(TAG, "Analysing event with type ${it.entityType}")
            // TODO: Do work here, deserialize the inner object and store it in the database
        }
    }

}