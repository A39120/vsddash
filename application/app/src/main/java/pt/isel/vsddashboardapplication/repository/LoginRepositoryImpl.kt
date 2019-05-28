package pt.isel.vsddashboardapplication.repository

import android.content.SharedPreferences
import android.util.Base64
import kotlinx.coroutines.Deferred
import pt.isel.vsddashboardapplication.communication.services.model.Session
import javax.inject.Inject

class LoginRepositoryImpl @Inject constructor(
        private val sharedPrefs : SharedPreferences
) : LoginRepository{
    companion object{
        private const val USERNAME_KEY = "username"
        private const val PASSWORD_KEY = "password"
        private const val ORGANIZATION_KEY = "organization"
    }

    override fun getUsername(): String? = sharedPrefs.getString(USERNAME_KEY, null)
    override fun getPassword(): String? = sharedPrefs.getString(PASSWORD_KEY, null)
    override fun getOrganization(): String? = sharedPrefs.getString(ORGANIZATION_KEY, null)

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

    override fun login() : Deferred<List<Session>>? {
        //TODO: Do this
        return null
    }

    private fun getEncodedAuthorization(username: String?, key: String?) : String?{

        if(username == null || key == null)
            return null

        val auth = "$username:$key".toByteArray(Charsets.UTF_8)
        val encoded = Base64.encode(auth, Base64.DEFAULT)
        return String(encoded)
    }
}