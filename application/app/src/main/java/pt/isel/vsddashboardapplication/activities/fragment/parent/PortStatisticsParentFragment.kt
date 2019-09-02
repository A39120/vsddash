package pt.isel.vsddashboardapplication.activities.fragment.parent

import android.util.Log
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.Navigation
import androidx.navigation.fragment.navArgs
import kotlinx.coroutines.*
import pt.isel.vsddashboardapplication.R
import pt.isel.vsddashboardapplication.activities.fragment.BaseFragment
import pt.isel.vsddashboardapplication.databinding.FragmentPortStatisticsBinding
import pt.isel.vsddashboardapplication.utils.getApm
import pt.isel.vsddashboardapplication.utils.getPerfMonitor
import pt.isel.vsddashboardapplication.utils.sharedPreferences
import pt.isel.vsddashboardapplication.viewmodel.ProbestatsViewModel

class PortStatisticsParentFragment : BaseFragment<ProbestatsViewModel, FragmentPortStatisticsBinding>() {
    companion object {
        private const val TAG = "FRAG/PORT_STATS"
    }

    override fun getLayoutRes(): Int = R.layout.fragment_port_statistics

    private val arguments: PortStatisticsParentFragmentArgs by navArgs()
    private val job = Job()
    private val repeatableScope = CoroutineScope(Dispatchers.IO + job)

    override fun observeViewModel() {}
    override fun setBindingObjects() {
        binding.settings.setOnClickListener {
            val directions = PortStatisticsParentFragmentDirections
                .actionPortStatisticsParentFragmentToDpiStatsSettingsFragment(
                    enterpriseId = (this.activity as MainActivity).enterpriseId!!,
                    nsg = arguments.nsg,
                    port = arguments.uplinkName
                )
            Navigation.findNavController(it).navigate(directions)
        }
    }

    override fun assignViewModel(): ProbestatsViewModel =
        ViewModelProviders.of(this, viewModelFactory)[ProbestatsViewModel::class.java]


    override fun initViewModel() {
        val port = arguments.uplinkName
        val system = arguments.nsg
        val apm = context?.sharedPreferences()?.getApm()
        val perfMonitor = context?.sharedPreferences()?.getPerfMonitor()
        Log.d( TAG, "Starting view model for port statistics for port $port of system $system, observing APM $apm and PF $perfMonitor" )
        viewModel.init(port, system, apm, perfMonitor)

    }

    override fun onPause() {
        super.onPause()
        Log.d(TAG, "Stopping job")
        job.cancel()
    }

    override fun onResume() {
        super.onResume()
        Log.d(TAG, "Resuming job")
        repeatableScope.launch {
            while(true) {
                delay(30000)
                viewModel.updateLiveData()
            }
        }
    }

}
