package pt.isel.vsddashboardapplication.viewmodel.authentication

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import pt.isel.vsddashboardapplication.model.Session
import pt.isel.vsddashboardapplication.repository.base.LoginRepository
import javax.inject.Inject

class LoginViewModel @Inject constructor(private val repository: LoginRepository): ViewModel() {
    companion object{
        private const val TAG = "VM/LOGIN"
    }

    var username : String? = null
    var password : String? = null
    var organization : String? = null

    fun init(){
        Log.d(TAG, "Initiating Login View Model")

        // Maybe change this later
        viewModelScope.launch(Dispatchers.Default) {
            //Attribute values
            username = repository.getUsername()
            password = repository.getPassword()
            organization = repository.getOrganization()
        }
    }

    fun updateUsername(username: String?)  =
        viewModelScope.launch(Dispatchers.Main) {
            Log.d(TAG, "Updating username")
            repository.updateUsername(username)
            this@LoginViewModel.username = username
        }


    fun updatePassword(password: String?) =
        viewModelScope.launch(Dispatchers.Default) {
            Log.d(TAG, "Updating password")
            repository.updatePassword(password)
            this@LoginViewModel.password = password
        }


    fun updateOrganization(organization: String?) =
        viewModelScope.launch(Dispatchers.Default) {
            Log.d(TAG, "Updating organization")
            repository.updateOrganization(organization)
            this@LoginViewModel.organization = organization
        }


    fun connect(): Deferred<List<Session>> {
        Log.d(TAG, "Starting login")
        return repository.login()
    }

}








