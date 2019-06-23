package pt.isel.vsddashboardapplication.repository

import androidx.lifecycle.LifecycleObserver
import kotlinx.coroutines.Deferred
import pt.isel.vsddashboardapplication.model.Session

interface LoginRepository  : LifecycleObserver{

    fun getUsername() : String?
    fun getPassword() : String?
    fun getOrganization() : String?

    fun updateUsername(username: String?)
    fun updatePassword(password: String?)
    fun updateOrganization(organization: String?)

    fun login() : Deferred<List<Session>>
}