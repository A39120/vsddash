package pt.isel.vsddashboardapplication.activities

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.UiThread
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.Navigation
import com.google.android.material.button.MaterialButton
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.async
import kotlinx.coroutines.cancelAndJoin
import kotlinx.coroutines.runBlocking
import pt.isel.vsddashboardapplication.R
import pt.isel.vsddashboardapplication.VsdApplication
import pt.isel.vsddashboardapplication.activities.listener.Watcher
import pt.isel.vsddashboardapplication.communication.services.model.Session
import pt.isel.vsddashboardapplication.databinding.LoginFragmentBinding
import pt.isel.vsddashboardapplication.repository.LoginRepository
import pt.isel.vsddashboardapplication.repository.LoginRepositoryImpl
import pt.isel.vsddashboardapplication.utils.sharedPreferences
import pt.isel.vsddashboardapplication.viewmodel.LoginViewModel

/**
 * Responsible for logging in, presents the login screen, so the user can login
 */
class LoginFragment : Fragment() {
    companion object {
        private const val TAG = "FRAG/LOGIN"
    }

    private lateinit var repo : LoginRepository
    private val viewModel : LoginViewModel by lazy {
        ViewModelProviders
            .of(this)
            .get(LoginViewModel::class.java)
    }

    private lateinit var binding : LoginFragmentBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.i(TAG, "Creating fragment")
        repo = LoginRepositoryImpl( this.context!!.sharedPreferences() )
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        Log.i(TAG, "Creating view")
        binding = DataBindingUtil.inflate(inflater, R.layout.login_fragment, container, false)
        viewModel.init(repo)

        binding.lifecycleOwner = this
        binding.viewmodel = viewModel

        binding.organization.addTextChangedListener( Watcher{ viewModel.updateOrganization(it.toString()) })
        binding.username.addTextChangedListener( Watcher{ viewModel.updateUsername(it.toString()) })
        binding.password.addTextChangedListener( Watcher{ viewModel.updatePassword(it.toString()) })

        //binding.settingsButton.setOnClickListener {}
        binding.settingsButton.setOnClickListener {
            Navigation.findNavController(it).navigate(R.id.action_loginFragment_to_apiSettingsFragment)
        }

        binding.connectButton.setOnClickListener{ onConnectPress(it as MaterialButton) }
        return binding.root
    }

    private fun connectionChangeButton(resId: Int) {
        Log.i(TAG, "Changing button text")
        binding.connectButton.setText(resId)
    }

    private fun onConnectPress(button: MaterialButton) {
        Log.i(TAG, "Connect button pressed")
        try {
            runBlocking {
                button.setText(R.string.connecting)
                val job = viewModel.connect()
                button.setOnClickListener { cancelConnection(it as MaterialButton, job) }
                async {
                    Log.i(TAG, "Awaiting for connection to finish")

                    // Await for job to complete and check if it has errors
                    job?.join()

                    val exceptions = job?.getCompletionExceptionOrNull()
                    if (exceptions != null) {
                        Log.w(TAG, "Exception $exceptions encountered")
                        connectionChangeButton(R.string.connection_failed)
                        return@async
                    }

                    val sessionList = job?.await()
                    if (sessionList == null) {
                        Log.e(TAG, "Session returned empty")
                        connectionChangeButton(R.string.connection_failed)

                    } else {
                        Log.i(TAG, "Setting Session")
                        val session = sessionList[0]
                        (this@LoginFragment.activity?.application as VsdApplication).setSession(session)
                        Navigation.findNavController(button).navigate(R.id.action_loginFragment_to_menuFragment)
                    }
                }
            }
        } catch (ex: Throwable){
            Log.e(TAG, ex.message)
        }
    }

    private fun cancelConnection(button: MaterialButton, job: Deferred<List<Session>>?)  {
        runBlocking {
            async {
                job?.cancelAndJoin()
                button.setText(R.string.button_connect)
                button.setOnClickListener{ onConnectPress(button) }
            }
        }
    }


}



