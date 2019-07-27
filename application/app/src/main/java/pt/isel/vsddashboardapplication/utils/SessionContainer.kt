package pt.isel.vsddashboardapplication.utils

import android.util.Log
import okhttp3.Credentials
import pt.isel.vsddashboardapplication.model.Session
import java.util.*

/**
 * Session container, contains operations related to the user
 */
class SessionContainer {
    companion object {
        private const val TAG = "SESSC"
    }

    /**
     * Username property, username of the username unable to get
     */
    var username: String? = null
        set(value){
            Log.d(TAG, "Setting username $username")
            field = value
        }

    /**
     * Session property, unable to be got
     */
    var session: Session? = null
        set(value){
            Log.d(TAG, "Setting session for $username with API Key: ${value?.APIKey}")
            field = value
        }

    fun getAuthorizationCredentials() : String = Credentials.basic(username, session?.APIKey)

    fun hasExpired() : Boolean = Date(session?.APIKeyExpiry?:0) < Date()

    fun getEnterpriseId() = session?.enterpriseID

    fun getEnterpriseName() = session?.enterpriseName

}