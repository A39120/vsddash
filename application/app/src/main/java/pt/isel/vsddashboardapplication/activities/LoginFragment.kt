package pt.isel.vsddashboardapplication.activities

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.Navigation
import com.google.android.material.button.MaterialButton
import kotlinx.coroutines.*
import pt.isel.vsddashboardapplication.R
import pt.isel.vsddashboardapplication.activities.listener.Watcher
import pt.isel.vsddashboardapplication.communication.services.RetrofitServiceGenerator
import pt.isel.vsddashboardapplication.communication.services.model.Session
import pt.isel.vsddashboardapplication.databinding.LoginFragmentBinding
import pt.isel.vsddashboardapplication.repository.LoginRepository
import pt.isel.vsddashboardapplication.repository.LoginRepositoryImpl
import pt.isel.vsddashboardapplication.utils.AddressBuilder
import pt.isel.vsddashboardapplication.utils.SharedPreferenceKeys
import pt.isel.vsddashboardapplication.utils.sharedPreferences
import pt.isel.vsddashboardapplication.viewmodel.LoginViewModel

import kotlin.IllegalArgumentException

/**
 * Responsible for logging in, presents the login screen, so the user can login
 */
class LoginFragment : Fragment() {

    private lateinit var repo : LoginRepository
    private val viewModel : LoginViewModel by lazy {
        ViewModelProviders
            .of(this)
            .get(LoginViewModel::class.java)
    }

    private lateinit var binding : LoginFragmentBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        repo = LoginRepositoryImpl( this.context!!.sharedPreferences() )
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
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

        binding.connectButton.setOnClickListener {
            val button = (it as MaterialButton)
            button.let {
                it.setText(R.string.connecting)
                it.isPressed = true

                runBlocking {
                    launch {
                        try {
                            val session = connect()
                            it.setOnClickListener {
                                session?.cancel()
                                (it as MaterialButton).setText(R.string.button_connect)
                            }

                            val trueSession = session?.await()?.get(0)
                            trueSession?.let {
                                trueSession.apiKey
                                trueSession.apiKeyExpiry
                            }
                        } catch (iae: IllegalArgumentException) {
                            it.setText(R.string.invalid_credentials)
                            throw iae
                        } catch (ex: Throwable) {
                            it.setText(R.string.connection_failed)
                            throw ex
                        }
                    }.invokeOnCompletion {
                        if(it == null) {
                            button.setText(R.string.connected)
                            Navigation.findNavController(button).navigate(R.id.action_loginFragment_to_menuFragment)
                        }
                    }
                }
            }

        }
        return binding.root
    }

    private suspend fun connect() : Deferred<List<Session>>? {

        val sp = this.context!!.sharedPreferences()
        val address = sp.getString(SharedPreferenceKeys.CURRENTADDRESS, SharedPreferenceKeys.DEFAULTADDRESS)
        val port = sp.getInt(SharedPreferenceKeys.CURRENTPORT, SharedPreferenceKeys.PORTDEFAULT)

        if(viewModel.organization == null || viewModel.username == null || viewModel.password == null)
            throw IllegalArgumentException()

        val service = RetrofitServiceGenerator.createAuthenticationService(
            AddressBuilder().build(address, port),
            viewModel.organization!!,
            viewModel.username!!,
            viewModel.password!!
        )

        return service?.authenticate()
    }

}



