package pt.isel.vsddashboardapplication.repository.implementation

import android.content.SharedPreferences
import android.util.Base64
import kotlinx.coroutines.Deferred
import pt.isel.vsddashboardapplication.communication.services.AuthenticationService
import pt.isel.vsddashboardapplication.communication.services.RetrofitServices
import pt.isel.vsddashboardapplication.communication.services.model.Session
import pt.isel.vsddashboardapplication.repository.LoginRepository
import pt.isel.vsddashboardapplication.utils.AddressBuilder
import pt.isel.vsddashboardapplication.utils.SharedPreferenceKeys
import javax.inject.Inject

class LoginRepositoryImpl @Inject constructor(
        private val sharedPrefs : SharedPreferences
) : LoginRepository {
    companion object{
        private const val USERNAME_KEY = "username"
        private const val PASSWORD_KEY = "password"
        private const val ORGANIZATION_KEY = "organization"
    }

    private var authenticationService: AuthenticationService? = null
    private var dirty = true

    override fun getUsername(): String? = sharedPrefs.getString(USERNAME_KEY, null)
    override fun getPassword(): String? = sharedPrefs.getString(PASSWORD_KEY, null)
    override fun getOrganization(): String? = sharedPrefs.getString(ORGANIZATION_KEY, null)

    override fun updateUsername(username: String?) {
        dirty = true
        sharedPrefs.edit().apply {
            this.putString(USERNAME_KEY, username)
            this.apply()
        }
    }

    override fun updatePassword(password: String?) {
        dirty = true
        sharedPrefs.edit().apply {
            this.putString(PASSWORD_KEY, password)
            this.apply()
        }
    }

    override fun updateOrganization(organization: String?) {
        dirty = true
        sharedPrefs.edit().apply {
            this.putString(ORGANIZATION_KEY, organization)
            this.apply()
        }
    }

    override fun login() : Deferred<List<Session>> {
        if(dirty) {
            sharedPrefs.let {
                val username = getUsername() ?: ""
                val password = getPassword() ?: ""
                val organization = getOrganization() ?: ""

                val address = it.getString(SharedPreferenceKeys.CURRENTADDRESS, null)
                val port = it.getInt(SharedPreferenceKeys.CURRENTPORT, -1)

                val api = AddressBuilder.build(address, port)
                this.authenticationService =
                    RetrofitServices
                        .getInstance(api, username, organization)
                        .createAuthenticationService(password)
            }
        }

        return authenticationService?.authenticate()!!

    }

    private fun getEncodedAuthorization(username: String?, key: String?) : String?{

        if(username == null || key == null)
            return null

        val auth = "$username:$key".toByteArray(Charsets.UTF_8)
        val encoded = Base64.encode(auth, Base64.DEFAULT)
        return String(encoded)
    }
}