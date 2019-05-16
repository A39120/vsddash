package pt.isel.vsddashboardapplication.repository

interface LoginRepository {

    fun getUsername() : String?
    fun getPassword() : String?
    fun getOrganization() : String?

    fun updateUsername(username: String?)
    fun updatePassword(password: String?)
    fun updateOrganization(organization: String?)
}