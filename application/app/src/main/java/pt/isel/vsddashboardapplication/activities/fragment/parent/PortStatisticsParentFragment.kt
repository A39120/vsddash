package pt.isel.vsddashboardapplication.activities.fragment.parent

import androidx.lifecycle.ViewModelProviders
import pt.isel.vsddashboardapplication.R
import pt.isel.vsddashboardapplication.activities.fragment.BaseFragment
import pt.isel.vsddashboardapplication.databinding.FragmentPortStatisticsBinding
import pt.isel.vsddashboardapplication.viewmodel.ProbestatsViewModel

class PortStatisticsParentFragment : BaseFragment<ProbestatsViewModel, FragmentPortStatisticsBinding>(){

    override fun getLayoutRes(): Int = R.layout.fragment_port_statistics

    override fun observeViewModel() { }

    override fun setBindingObjects() { }

    override fun assignViewModel(): ProbestatsViewModel =
        ViewModelProviders.of(this, viewModelFactory)[ProbestatsViewModel::class.java]

    override fun initViewModel() {
        //viewModel.init()
    }
}