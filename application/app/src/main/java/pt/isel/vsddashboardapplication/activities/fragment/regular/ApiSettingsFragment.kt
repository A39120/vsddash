package pt.isel.vsddashboardapplication.activities.fragment.regular

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.Navigation
import pt.isel.vsddashboardapplication.R
import pt.isel.vsddashboardapplication.activities.fragment.BaseFragment
import pt.isel.vsddashboardapplication.activities.listener.Watcher
import pt.isel.vsddashboardapplication.databinding.FragmentApiSettingsBinding
import pt.isel.vsddashboardapplication.viewmodel.authentication.ApiSettingsViewModel

/**
 * Fragment responsible for setting up the API settings, like the address and ports
 */
class ApiSettingsFragment : BaseFragment<ApiSettingsViewModel, FragmentApiSettingsBinding>() {

    override fun observeViewModel() {
        binding.address.setText(viewModel.address)
        binding.apiPort.setText(viewModel.apiPort.toString())
        binding.esPort.setText(viewModel.esPort.toString())
        binding.executePendingBindings()
    }

    override fun getLayoutRes(): Int = R.layout.fragment_api_settings

    override fun assignViewModel(): ApiSettingsViewModel =
        ViewModelProviders.of(this, viewModelFactory)[ApiSettingsViewModel::class.java]

    override fun initViewModel() { }

    override fun setBindingObjects() {
        //binding.address.setText(viewModel.address)
        //binding.apiPort.setText(viewModel.apiPort.toString())
        //binding.esPort.setText(viewModel.esPort.toString())

        /**
        binding.address.addTextChangedListener(Watcher{ viewModel.updateAddress(it.toString())})
        binding.apiPort.addTextChangedListener(Watcher{
            if(it.toString() != "")
                viewModel.updateApiPort(Integer.parseInt(it.toString()))
            else viewModel.updateApiPort(null)
        })

        binding.esPort.addTextChangedListener(Watcher{
            if(it.toString() != "")
                viewModel.updateElasticSearchPort(Integer.parseInt(it.toString()))
            else
                viewModel.updateElasticSearchPort(null)
        })
         */

        binding.saveButton.setOnClickListener {
            binding.address.text.toString().run {
                viewModel.updateAddress(this)
            }
            binding.apiPort.text.toString().run {
                val port = if(this == "") null else Integer.parseInt(this)
                viewModel.updateApiPort(port)
            }
            binding.esPort.text.toString().run {
                val port = if(this == "") null else Integer.parseInt(this)
                viewModel.updateElasticSearchPort(port)
            }
            val destinations = ApiSettingsFragmentDirections.actionApiSettingsFragmentToLoginFragment()
            Navigation.findNavController(it).navigate(destinations)
        }
        binding.executePendingBindings()
    }


}