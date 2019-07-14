package pt.isel.vsddashboardapplication.service

import android.content.Context
import android.os.Build
import androidx.work.*
import pt.isel.vsddashboardapplication.repository.services.RetrofitSingleton
import pt.isel.vsddashboardapplication.utils.SessionContainer
import java.time.Duration
import java.util.concurrent.TimeUnit

/**
 * Login worker in charge of (re)authenticating the user after the API Key has expired
 */
class LoginWorker(appContext: Context, workParams: WorkerParameters) : CoroutineWorker(appContext, workParams){

    companion object {
        fun enqueue(session: SessionContainer) : Operation? {
            return session.session?.let {
                val expiry = it.APIKeyExpiry?:0
                val workRequest : WorkRequest = PeriodicWorkRequestBuilder<LoginWorker>(expiry, TimeUnit.MILLISECONDS)
                    .build()

                WorkManager.getInstance().enqueue(workRequest)
            }
        }

    }

    /**
     * Tries to authenticate the user
     */
    override suspend fun doWork(): Result {
        RetrofitSingleton.authenticationService()?.authenticate() ?: return Result.failure()
        return Result.success()
    }


}