package pt.isel.vsddashboardapplication.viewmodel

import androidx.lifecycle.ViewModel
import pt.isel.vsddashboardapplication.repository.LoginRepository

class LoginViewModel : ViewModel() {

    private lateinit var repo: LoginRepository

    var username : String? = null
    var password : String? = null
    var organization : String? = null

    fun init(repo: LoginRepository){
        this.repo = repo

        //Attribute values
        this.username = repo.getUsername()
        this.password = repo.getPassword()
        this.organization = repo.getOrganization()
    }

    fun updateUsername(username: String?) : Unit = repo.updateUsername(username)
    fun updatePassword(password: String?) : Unit = repo.updateUsername(password)
    fun updateOrganization(organization: String?) : Unit = repo.updateOrganization(organization)

}








