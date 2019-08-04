package pt.isel.vsddashboardapplication.activities.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import pt.isel.vsddashboardapplication.R
import pt.isel.vsddashboardapplication.databinding.FragmentNsgatewayStatisticsBinding
import kotlin.coroutines.CoroutineContext
import pt.isel.vsddashboardapplication.viewmodel.NSGStatisticsViewModel

/**
 * Fragment associated with the statistics of a NSG
 */
class NSGatewayStatisticsFragment : Fragment(), CoroutineScope {

    /**
     * Scope of fragment
     */
    private val job = SupervisorJob()
    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main + job

    /**
     * Variables of fragment - ViewModel, Repository
     */
    private lateinit var viewModel : NSGStatisticsViewModel
    private lateinit var binding: FragmentNsgatewayStatisticsBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        viewModel = ViewModelProviders.of(this).get(NSGStatisticsViewModel::class.java)
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_nsgateway_statistics, container, false)
        //binding.viewModel = viewModel
        binding.lifecycleOwner = this.viewLifecycleOwner
        return binding.root
    }

}