package pt.isel.vsddashboardapplication.service

import android.content.Context
import android.os.Build
import android.util.Log
import androidx.work.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import pt.isel.vsddashboardapplication.VsdApplication
import pt.isel.vsddashboardapplication.model.Session
import pt.isel.vsddashboardapplication.repository.services.RetrofitSingleton
import pt.isel.vsddashboardapplication.utils.SessionContainer
import pt.isel.vsddashboardapplication.utils.SharedPreferencesUtils
import pt.isel.vsddashboardapplication.utils.setApiExpiration
import pt.isel.vsddashboardapplication.utils.setApiKey
import java.time.Duration
import java.util.concurrent.TimeUnit

/**
 * Login worker in charge of (re)authenticating the user after the API Key has expired
 */
class LoginWorker(appContext: Context, workParams: WorkerParameters) : CoroutineWorker(appContext, workParams){
    companion object {
        private const val TAG = "WORK/LOGIN"
        fun enqueue(session: Session?) : Operation? {
            return session.let {
                val expiry = it?.APIKeyExpiry?: 0
                val workRequest : WorkRequest = OneTimeWorkRequestBuilder<LoginWorker>()
                    .setInitialDelay(expiry, TimeUnit.MILLISECONDS)
                    .build()

                WorkManager.getInstance().enqueue(workRequest)
            }
        }
    }


    /**
     * Tries to authenticate the user
     */
    override suspend fun doWork(): Result {
        val auth = RetrofitSingleton.authenticationService()?.authenticate() ?:
             return Result.failure()

        withContext(Dispatchers.IO){
            try {
                auth.await().run {
                    Log.d(TAG, "Got authentication information")
                    val session = this.first()
                    val sp = SharedPreferencesUtils.createOrGet(applicationContext)
                    sp.apply {
                        setApiKey(session.APIKey)
                        setApiExpiration(session.APIKeyExpiry)
                        RetrofitSingleton.setupRetrofit(session.APIKey)
                        LoginWorker.enqueue(session)
                    }

                }
            } catch (ex: Exception) {
                Log.d(TAG, "Exception found - ${ex.message}")
                Result.retry()
            }
        }
        return Result.success()
    }


}