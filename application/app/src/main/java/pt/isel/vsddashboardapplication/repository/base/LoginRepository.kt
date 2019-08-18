package pt.isel.vsddashboardapplication.repository.base

import kotlinx.coroutines.Deferred
import pt.isel.vsddashboardapplication.model.Session

interface LoginRepository  {

    fun getUsername() : String?
    fun getPassword() : String?
    fun getOrganization() : String?

    fun updateUsername(username: String?)
    fun updatePassword(password: String?)
    fun updateOrganization(organization: String?)

    fun login() : Deferred<List<Session>>?
}