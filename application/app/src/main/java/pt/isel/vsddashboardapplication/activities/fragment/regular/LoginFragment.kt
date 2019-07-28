package pt.isel.vsddashboardapplication.activities.fragment.regular

import android.util.Log
import android.view.View
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.Navigation
import kotlinx.coroutines.*
import pt.isel.vsddashboardapplication.R
import pt.isel.vsddashboardapplication.VsdApplication
import pt.isel.vsddashboardapplication.activities.fragment.base.BaseFragment
import pt.isel.vsddashboardapplication.activities.listener.Watcher
import pt.isel.vsddashboardapplication.databinding.FragmentLoginBinding
import pt.isel.vsddashboardapplication.repository.services.RetrofitSingleton
import pt.isel.vsddashboardapplication.viewmodel.authentication.LoginViewModel
import kotlin.coroutines.CoroutineContext

/**
 * Responsible for logging in, presents the login screen, so the user can login
 */
class LoginFragment : BaseFragment<LoginViewModel, FragmentLoginBinding>(), CoroutineScope {
    companion object { private const val TAG = "FRAG/LOGIN" }

    /**
     * Changes the connect button to the defined values
     */
    private suspend fun changeConnectButton(status: ButtonStatus, job: Job? = null) {
        Log.d(TAG, "Changing the connect button to status $status.")
        val connect = View.OnClickListener {
            CoroutineScope(Dispatchers.Main).launch {startAuthentication() }
        }

        val cancel = View.OnClickListener {
            CoroutineScope(Dispatchers.Main).launch { cancelAuthentication(job!!) }
        }

        when (status) {
            ButtonStatus.CONNECT -> changeButton(R.string.button_connect, connect)
            ButtonStatus.INPROGRESS -> changeButton(R.string.connecting, cancel)
            ButtonStatus.ERROR -> changeButton(R.string.connection_failed, connect)
        }
    }
    /**
     * The Main UI Context, needed for nested functions inside IO/Default dispatchers
     */
    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main + SupervisorJob()


    override fun assignViewModel(): LoginViewModel =
        ViewModelProviders
            .of(this, viewModelFactory)
            .get(LoginViewModel::class.java)

    override fun initViewModel() { viewModel.init() }

    override fun getLayoutRes(): Int = R.layout.fragment_login

    override fun observeViewModel() {
        binding.organization.addTextChangedListener(Watcher { viewModel.updateOrganization(it.toString()) })
        binding.username.addTextChangedListener(Watcher { viewModel.updateUsername(it.toString()) })
        binding.password.addTextChangedListener(Watcher { viewModel.updatePassword(it.toString()) })
    }

    override fun setBindingObjects() {
        setSettings()
        binding.viewmodel = viewModel
        launch { changeConnectButton(ButtonStatus.CONNECT) }
        binding.executePendingBindings()
    }

    /**
     * Sets the settings button
     */
    private fun setSettings() {
        binding.settingsButton.setOnClickListener {
            Navigation.findNavController(it).navigate(R.id.action_loginFragment_to_apiSettingsFragment)
        }
    }

    /**
     * Changes connect button
     * @resId is the integer id of the string resource
     * @listener the listener for the new login button
     */
    private suspend fun changeButton(resId: Int, listener: View.OnClickListener) = withContext(Dispatchers.Main) {
        Log.d(TAG, "Changing connect button to $resId")
        binding.connectButton.setText(resId)
        binding.connectButton.setOnClickListener(listener)
    }

    /**
     * The Status of the Connect button, used to track the status of
     * the button
     */
    private enum class ButtonStatus { CONNECT, INPROGRESS, ERROR }

    /**
     * Starts an asynchronous job that connects to the API
     * @return an async job
     */
    private suspend fun startAuthentication() = withContext(Dispatchers.IO) {
        Log.d(TAG, "Starting the authentication.")
        try {
            RetrofitSingleton.setupAuthenticator(viewModel.password)

            val job = viewModel.connect()
            changeConnectButton(ButtonStatus.INPROGRESS, job)

            job.join()
            Log.d(TAG, "Finished authentication")
            if(job.isCancelled)
                return@withContext

            val sessions = job.await()

            // Move to another fragment
            (this@LoginFragment.activity?.application as VsdApplication).session.let {
                it.username = binding.username.text.toString()
                it.session = sessions[0]
                RetrofitSingleton.setupRetrofit(sessions[0].APIKey)
            }
            //Navigation.findNavController(this@LoginFragment.view!!).navigate(R.id.action_loginFragment_to_menuFragment)
            Navigation.findNavController(this@LoginFragment.view!!).navigate(R.id.action_loginFragment_to_enterpriseListFragment)
        } catch (ex: Throwable) {
            Log.e(TAG, "Authentication error occurred.\n ${ex.message}")
            changeConnectButton(ButtonStatus.ERROR)
        }
        Log.i(TAG, "End authentication")
    }

    /**
     * Starts an asynchronous job that cancels the authentication
     * @return an async job that cancels previous job
     */
    private suspend fun cancelAuthentication(job: Job) = withContext(Dispatchers.IO) {
        Log.i(TAG, "Canceling the authentication job")
        changeConnectButton(ButtonStatus.CONNECT)
        job.cancelAndJoin()
    }

}


