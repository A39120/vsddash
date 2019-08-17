package pt.isel.vsddashboardapplication.activities.fragment.parent

import androidx.lifecycle.ViewModelProviders
import pt.isel.vsddashboardapplication.R
import pt.isel.vsddashboardapplication.activities.fragment.BaseFragment
import pt.isel.vsddashboardapplication.databinding.FragmentPortStatisticsBinding
import pt.isel.vsddashboardapplication.viewmodel.PortSettingsViewModel

class PortSettingsFragment : BaseFragment<PortSettingsViewModel, FragmentPortStatisticsBinding>() {

    override fun getLayoutRes(): Int = R.layout.fragment_port_statistics

    override fun observeViewModel() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun setBindingObjects() { }

    override fun assignViewModel(): PortSettingsViewModel =
        ViewModelProviders.of(this, viewModelFactory)[PortSettingsViewModel::class.java]

    override fun initViewModel() {
        //viewModel.init()
    }

}
