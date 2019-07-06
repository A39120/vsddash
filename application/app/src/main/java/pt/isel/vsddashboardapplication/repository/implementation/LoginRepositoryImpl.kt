package pt.isel.vsddashboardapplication.repository.implementation

import android.content.SharedPreferences
import kotlinx.coroutines.Deferred
import pt.isel.vsddashboardapplication.repository.services.vsd.AuthenticationService
import pt.isel.vsddashboardapplication.repository.services.RetrofitServices
import pt.isel.vsddashboardapplication.model.Session
import pt.isel.vsddashboardapplication.repository.LoginRepository
import pt.isel.vsddashboardapplication.utils.AddressBuilder
import pt.isel.vsddashboardapplication.utils.SharedPreferenceKeys
import javax.inject.Inject

/**
 * The implementation of the LoginRepository, responsible for
 * the authentication of the user
 */
class LoginRepositoryImpl @Inject constructor(
        private val sharedPrefs : SharedPreferences
) : LoginRepository {
    companion object{
        private const val USERNAME_KEY = "username"
        private const val PASSWORD_KEY = "password"
        private const val ORGANIZATION_KEY = "organization"

        private const val TAG = "REPO/LOGIN"

    }

    private var authenticationService: AuthenticationService? = null

    override fun getUsername(): String? =
            sharedPrefs.getString(USERNAME_KEY, null)

    override fun getPassword(): String? =
            sharedPrefs.getString(PASSWORD_KEY, null)

    override fun getOrganization(): String? =
            sharedPrefs.getString(ORGANIZATION_KEY, null)

    override fun updateUsername(username: String?) {
        sharedPrefs.edit().apply {
            this.putString(USERNAME_KEY, username)
            this.apply()
        }
    }

    override fun updatePassword(password: String?) {
        sharedPrefs.edit().apply {
            this.putString(PASSWORD_KEY, password)
            this.apply()
        }
    }

    override fun updateOrganization(organization: String?) {
        sharedPrefs.edit().apply {
            this.putString(ORGANIZATION_KEY, organization)
            this.apply()
        }
    }

    /**
     * Starts the login process
     */
    override fun login() : Deferred<List<Session>> {
        sharedPrefs.let {
            val username = getUsername()?: ""
            val password = getPassword()?: ""
            val organization = getOrganization() ?: ""

            val address = it.getString(SharedPreferenceKeys.CURRENTADDRESS, null)
            val port = it.getInt(SharedPreferenceKeys.CURRENTPORT, -1)

            val api = AddressBuilder.build(address?:"", port)
            this.authenticationService =
                RetrofitServices
                    .getInstance(api, username, organization)
                    .createAuthenticationService(password)
        }

        return authenticationService?.authenticate()!!
    }

}
