package pt.isel.vsddashboardapplication.repository.base.implementation

import android.content.SharedPreferences
import android.util.Log
import kotlinx.coroutines.Deferred
import pt.isel.vsddashboardapplication.repository.services.vsd.AuthenticationService
import pt.isel.vsddashboardapplication.model.Session
import pt.isel.vsddashboardapplication.repository.base.LoginRepository
import pt.isel.vsddashboardapplication.repository.services.ElasticSearchRetrofitSingleton
import pt.isel.vsddashboardapplication.repository.services.RetrofitSingleton
import pt.isel.vsddashboardapplication.utils.*
import javax.inject.Inject

/**
 * The implementation of the LoginRepository, responsible for
 * the authentication of the user
 */
class LoginRepositoryImpl @Inject constructor(
    private val sharedPrefs : SharedPreferences
) : LoginRepository {
    companion object{ private const val TAG = "REPO/LOGIN" }

    private var authenticationService: AuthenticationService? = null

    override fun getUsername(): String? = sharedPrefs.getUsername()
    override fun getPassword(): String? = sharedPrefs.getPassword()
    override fun getOrganization(): String? = sharedPrefs.getOrganization()

    override fun updateUsername(username: String?) {
        Log.d(TAG, "Updating username: $username")
        sharedPrefs.setUsername(username)
    }

    override fun updatePassword(password: String?) {
        Log.d(TAG, "Updating password")
        sharedPrefs.setPassword(password)
    }

    override fun updateOrganization(organization: String?) {
        Log.d(TAG, "Updating organization: $organization")
        sharedPrefs.setOrganization(organization)
    }

    /**
     * Starts the login process
     */
    override fun login() : Deferred<List<Session>>? {
        Log.d(TAG, "LOGIN - Starting login")
        sharedPrefs.let {
            val username = getUsername() ?: ""
            val password = getPassword() ?: ""
            val organization = getOrganization() ?: ""

            val address = it.getAddress()
            val port = it.getVsdPort()
            val esPort = it.getElasticSearchPort()

            val api = AddressBuilder.build(address ?: "", port)
            Log.d(TAG, "LOGIN - Creating Authentication service")
            sharedPrefs.let {
                RetrofitSingleton.prepareVsdService(api, username, organization)
                ElasticSearchRetrofitSingleton.set(AddressBuilder.build(address?:"", esPort))
                RetrofitSingleton.setupAuthenticator(password)
                this.authenticationService = RetrofitSingleton.authenticationService()
            }

            Log.d(TAG, "LOGIN - Authenticating with username $username and organization $organization ($api)")
            return authenticationService?.authenticate()
        }
    }

}
