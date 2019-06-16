package pt.isel.vsddashboardapplication.activities.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.Navigation
import kotlinx.coroutines.*
import pt.isel.vsddashboardapplication.R
import pt.isel.vsddashboardapplication.VsdApplication
import pt.isel.vsddashboardapplication.activities.listener.Watcher
import pt.isel.vsddashboardapplication.databinding.LoginFragmentBinding
import pt.isel.vsddashboardapplication.repository.LoginRepository
import pt.isel.vsddashboardapplication.repository.implementation.LoginRepositoryImpl
import pt.isel.vsddashboardapplication.utils.sharedPreferences
import pt.isel.vsddashboardapplication.viewmodel.LoginViewModel
import kotlin.coroutines.CoroutineContext

/**
 * Responsible for logging in, presents the login screen, so the user can login
 */
class LoginFragment : Fragment(), CoroutineScope {
    companion object {
        private const val TAG = "FRAG/LOGIN"
    }

    /**
     * Co-routine for the fragment
     */
    private val job = SupervisorJob()
    override val coroutineContext: CoroutineContext = Dispatchers.Main + job

    private val repo: LoginRepository by lazy {
        LoginRepositoryImpl(this.context!!.sharedPreferences())
    }

    private val viewModel: LoginViewModel by lazy {
        ViewModelProviders
            .of(this)
            .get(LoginViewModel::class.java)
    }

    private lateinit var binding: LoginFragmentBinding

    /**
     * Sets the initial Login View
     */
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        Log.i(TAG, "Creating view")
        binding = DataBindingUtil.inflate(inflater, R.layout.login_fragment, container, false)
        viewModel.init(repo)

        binding.lifecycleOwner = this
        binding.viewmodel = viewModel

        binding.organization.addTextChangedListener(Watcher { viewModel.updateOrganization(it.toString()) })
        binding.username.addTextChangedListener(Watcher { viewModel.updateUsername(it.toString()) })
        binding.password.addTextChangedListener(Watcher { viewModel.updatePassword(it.toString()) })

        setSettings()
        changeConnectButton(ButtonStatus.CONNECT)

        return binding.root
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
    private fun changeButton(resId: Int, listener: View.OnClickListener) = launch(coroutineContext) {
        binding.connectButton.setText(resId)
        binding.connectButton.setOnClickListener(listener)
    }

    private enum class ButtonStatus { CONNECT, INPROGRESS, ERROR }

    /**
     * Changes the connect button to the defined values
     */
    private fun changeConnectButton(status: ButtonStatus, job: Job? = null) {
        Log.i(TAG, "Changing the connect button.")
        val connect = View.OnClickListener { launch {
            //changeConnectButton(ButtonStatus.INPROGRESS)
            startAuthentication()
        } }
        val cancel = View.OnClickListener { launch { cancelAuthentication(job!!) } }

        when (status) {
            ButtonStatus.CONNECT -> changeButton(R.string.button_connect, connect)
            ButtonStatus.INPROGRESS -> changeButton(R.string.connecting, cancel)
            ButtonStatus.ERROR -> changeButton(R.string.connection_failed, connect)
        }
    }

    /**
     * Starts an asynchronous job that connects to the API
     * @return an async job
     */
    private suspend fun startAuthentication() = withContext(Dispatchers.IO) {
        Log.i(TAG, "Starting the authentication.")

        val job = viewModel.connect()
        changeConnectButton(ButtonStatus.INPROGRESS, job)

        job.join()
        try {
            if(job.isCancelled)
                return@withContext
            val sessions = job.await()

            // Move to another fragment
            (this@LoginFragment.activity?.application as VsdApplication).session = sessions[0]
            Navigation.findNavController(this@LoginFragment.view!!).navigate(R.id.action_loginFragment_to_menuFragment)
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
        Log.i(TAG, "Canceling the authentication the job")
        changeConnectButton(ButtonStatus.CONNECT)
        job.cancelAndJoin()
    }

}


