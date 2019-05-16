package pt.isel.vsddashboardapplication.activities

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import pt.isel.vsddashboardapplication.R
import pt.isel.vsddashboardapplication.activities.listener.Watcher
import pt.isel.vsddashboardapplication.databinding.LoginFragmentBinding
import pt.isel.vsddashboardapplication.repository.LoginRepository
import pt.isel.vsddashboardapplication.repository.LoginRepositoryImpl
import pt.isel.vsddashboardapplication.utils.sharedPreferences
import pt.isel.vsddashboardapplication.viewmodel.LoginViewModel

/**
 * Responsible for logging in, presents the login screen, so the user can login
 */
class LoginFragment : Fragment(){

    private lateinit var repo : LoginRepository
    private val viewModel : LoginViewModel by lazy {
        ViewModelProviders
            .of(this)
            .get(LoginViewModel::class.java)
    }

    private lateinit var binding : LoginFragmentBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        repo  = LoginRepositoryImpl(this.context!!.sharedPreferences())
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.login_fragment, container, false)
        viewModel.init(repo)
        binding.lifecycleOwner = this
        binding.viewmodel = viewModel

        binding.organization.addTextChangedListener( Watcher{ viewModel.updateOrganization(it.toString()) })
        binding.username.addTextChangedListener( Watcher{ viewModel.updateUsername(it.toString()) })
        binding.password.addTextChangedListener( Watcher{ viewModel.updatePassword(it.toString()) })

        return binding.root
    }


}