package pt.isel.vsddashboardapplication.viewmodel.authentication

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import pt.isel.vsddashboardapplication.repository.LoginRepository

class LoginViewModel : ViewModel() {

    private lateinit var repo: LoginRepository
    var username : String? = null
    var password : String? = null
    var organization : String? = null

    fun init(repo: LoginRepository){
        this.repo = repo

        // Maybe change this later
        viewModelScope.launch(Dispatchers.Default) {
            //Attribute values
            username = repo.getUsername()
            password = repo.getPassword()
            organization = repo.getOrganization()
        }
    }

    fun updateUsername(username: String?)  =
        viewModelScope.launch(Dispatchers.Main) {
            repo.updateUsername(username)
            this@LoginViewModel.username = username
        }


    fun updatePassword(password: String?) =
        viewModelScope.launch(Dispatchers.Default) {
            repo.updatePassword(password)
            this@LoginViewModel.password = password
        }


    fun updateOrganization(organization: String?) =
        viewModelScope.launch(Dispatchers.Default) {
            repo.updateOrganization(organization)
            this@LoginViewModel.organization = organization
        }


    fun connect() =  repo.login()

}








