package pt.isel.vsddashboardapplication.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import pt.isel.vsddashboardapplication.repository.LoginRepository

class LoginViewModel : ViewModel() {

    private lateinit var repo: LoginRepository

    var username : String? = null
    var password : String? = null
    var organization : String? = null

    fun init(repo: LoginRepository){
        this.repo = repo

        viewModelScope.launch {
            //Attribute values
            username = repo.getUsername()
            password = repo.getPassword()
            organization = repo.getOrganization()
        }
    }

    fun updateUsername(username: String?) : Unit {
        viewModelScope.launch {
            repo.updateUsername(username)
            this@LoginViewModel.username = username
        }
    }

    fun updatePassword(password: String?) : Unit {
        viewModelScope.launch {
            repo.updatePassword(password)
            this@LoginViewModel.password = password
        }
    }

    fun updateOrganization(organization: String?) : Unit {
        viewModelScope.launch {
            repo.updateOrganization(organization)
            this@LoginViewModel.organization = organization
        }
    }

    suspend fun connect() = repo.login()

}








