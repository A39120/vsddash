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
import pt.isel.vsddashboardapplication.databinding.ApiSettingsFragmentBinding
import pt.isel.vsddashboardapplication.viewmodel.ApiSettingsViewModel
import pt.isel.vsddashboardapplication.repository.ApiSettingsRepoImpl
import pt.isel.vsddashboardapplication.utils.sharedPreferences
import java.lang.StringBuilder

class ApiSettingsFragment : Fragment() {


    val viewModel: ApiSettingsViewModel by lazy {
        ViewModelProviders.of(this)
            .get(ApiSettingsViewModel::class.java)
    }

    lateinit var binding : ApiSettingsFragmentBinding

    override fun onCreateView(inflater: LayoutInflater,
                              container: ViewGroup?,
                              savedInstanceState: Bundle?) : View? {

        binding = DataBindingUtil.inflate(inflater, R.layout.api_settings_fragment, container, false)
        viewModel.init(ApiSettingsRepoImpl(this.context!!.sharedPreferences()))

        binding.lifecycleOwner = this
        binding.viewmodel = viewModel

        //Add listeners
        binding.address.addTextChangedListener(Watcher{ viewModel.updateAddress(it.toString())})
        binding.apiPort.addTextChangedListener(Watcher{ viewModel.updateApiPort(Integer.parseInt(it.toString()))})
        binding.address.addTextChangedListener(Watcher{ viewModel.updateMonitPort(Integer.parseInt(it.toString()))})
        binding.connectButton.setOnContextClickListener { connect(it) }

        return binding.root
    }

    private fun buildUri() : String? {
        val address = viewModel.address.value
        val port = viewModel.address.value

        if(address == null){
            // TODO: Do something here, like a warning or exception
            return null
        }

        val addrBuilder = StringBuilder(address)
        if(!address.startsWith("https://")){
            // Doesn't start with https, therefore let's add it
            addrBuilder.insert(0, "https://")
        }

        // Add the port
        return addrBuilder.append(':' + (port?:8433).toString()).toString()
    }

    private fun connect(view: View) : Boolean{
        val uri = buildUri()
        //val button = binding.connect_button
        return false
    }
}