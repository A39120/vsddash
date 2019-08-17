package pt.isel.vsddashboardapplication.activities.fragment.regular

import androidx.lifecycle.ViewModelProviders
import pt.isel.vsddashboardapplication.R
import pt.isel.vsddashboardapplication.activities.fragment.BaseFragment
import pt.isel.vsddashboardapplication.activities.listener.Watcher
import pt.isel.vsddashboardapplication.databinding.FragmentApiSettingsBinding
import pt.isel.vsddashboardapplication.viewmodel.authentication.ApiSettingsViewModel

/**
 * Fragment responsible for setting up the API settings, like the address and ports
 */
class ApiSettingsFragment : BaseFragment<ApiSettingsViewModel, FragmentApiSettingsBinding>() {

    override fun observeViewModel() { }

    override fun getLayoutRes(): Int = R.layout.fragment_api_settings

    override fun assignViewModel(): ApiSettingsViewModel =
        ViewModelProviders.of(this, viewModelFactory)[ApiSettingsViewModel::class.java]

    override fun initViewModel() {
        viewModel.init()
    }

    override fun setBindingObjects() {
        binding.address.addTextChangedListener(Watcher{ viewModel.updateAddress(it.toString())})
        binding.apiPort.addTextChangedListener(Watcher{
            if(it.toString() != "")
                viewModel.updateApiPort(Integer.parseInt(it.toString()))
            else viewModel.updateApiPort(null)
        })

        binding.monitPort.addTextChangedListener(Watcher{
            if(it.toString() != "")
                viewModel.updateMonitPort(Integer.parseInt(it.toString()))
            else viewModel.updateMonitPort(null)
        })

        binding.saveButton.setOnClickListener { this.fragmentManager?.popBackStack() }

    }

}