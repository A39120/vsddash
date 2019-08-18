package pt.isel.vsddashboardapplication.activities.fragment.parent

import android.util.Log
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.navArgs
import pt.isel.vsddashboardapplication.R
import pt.isel.vsddashboardapplication.activities.fragment.BaseFragment
import pt.isel.vsddashboardapplication.databinding.FragmentPortStatisticsBinding
import pt.isel.vsddashboardapplication.viewmodel.ProbestatsViewModel

class PortStatisticsParentFragment : BaseFragment<ProbestatsViewModel, FragmentPortStatisticsBinding>(){
    companion object{ private const val TAG = "FRAG/PORT_STATS"}

    override fun getLayoutRes(): Int = R.layout.fragment_port_statistics

    private val arguments : PortStatisticsParentFragmentArgs by navArgs()

    override fun observeViewModel() {}
    override fun setBindingObjects() {}

    override fun assignViewModel(): ProbestatsViewModel =
        ViewModelProviders.of(this, viewModelFactory)[ProbestatsViewModel::class.java]

    override fun initViewModel() {
        val port = arguments.uplinkName
        val system = arguments.nsg
        val apm = arguments.apmName
        val perfMonitor = arguments.npmName
        Log.d(TAG, "Starting view model for port statistics for port $port of system $system, observing APM $apm and PF $perfMonitor")
        viewModel.init(port, system)

    }
}