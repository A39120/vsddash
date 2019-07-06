package pt.isel.vsddashboardapplication.viewmodel.authentication

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import pt.isel.vsddashboardapplication.repository.LoginRepository
import javax.inject.Inject

class LoginViewModel : ViewModel() {
    companion object{
        private const val TAG = "VM/LOGIN"
    }

    @Inject
    lateinit var repo: LoginRepository

    var username : String? = null
    var password : String? = null
    var organization : String? = null

    fun init(repo: LoginRepository){
        Log.d(TAG, "Initiating Login View Model")
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
            Log.d(TAG, "Updating username")
            repo.updateUsername(username)
            this@LoginViewModel.username = username
        }


    fun updatePassword(password: String?) =
        viewModelScope.launch(Dispatchers.Default) {
            Log.d(TAG, "Updating password")
            repo.updatePassword(password)
            this@LoginViewModel.password = password
        }


    fun updateOrganization(organization: String?) =
        viewModelScope.launch(Dispatchers.Default) {
            Log.d(TAG, "Updating organization")
            repo.updateOrganization(organization)
            this@LoginViewModel.organization = organization
        }


    fun connect() {
        Log.d(TAG, "Starting login")
        repo.login()
    }

}








