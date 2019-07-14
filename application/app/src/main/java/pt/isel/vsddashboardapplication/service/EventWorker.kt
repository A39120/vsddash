package pt.isel.vsddashboardapplication.service

import android.content.Context
import android.util.Log
import androidx.work.*
import pt.isel.vsddashboardapplication.model.events.Event
import pt.isel.vsddashboardapplication.repository.EventRepository
import pt.isel.vsddashboardapplication.repository.implementation.EventRepositoryImpl
import pt.isel.vsddashboardapplication.repository.services.RetrofitSingleton
import java.util.concurrent.Executor
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
                .setRequiresDeviceIdle(false)
                .build()

            val workRequest = OneTimeWorkRequestBuilder<EventWorker>()
                .setConstraints(constrains)
                .build()

            return WorkManager.getInstance().enqueue(workRequest)
        }

    }

    private val eventRepository: EventRepository by lazy { EventRepositoryImpl() }

    /**
     * Will listen to events, if successful it will analyze the event and do work with it, it will
     * retry upon success. It will stop doing work if something went wrong - can't connect to the
     * API for example
     * @return Result.retry(): to retry the job
     *         Result.failure(): if it failed
     */
    override suspend fun doWork(): Result {
        Log.d(TAG, "Getting event.")
        eventRepository.request() ?: return Result.failure()
        return Result.retry()
    }

    /**
     * Will do work with the list of events received
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