package pt.isel.vsddashboardapplication.activities.fragment.regular

import androidx.annotation.LayoutRes
import androidx.lifecycle.Observer
import androidx.navigation.Navigation
import kotlinx.coroutines.*

import pt.isel.vsddashboardapplication.R
import pt.isel.vsddashboardapplication.activities.fragment.parent.NsgPagerFragment
import pt.isel.vsddashboardapplication.activities.fragment.BaseFragment
import pt.isel.vsddashboardapplication.activities.fragment.base.IRefreshableComponent
import pt.isel.vsddashboardapplication.activities.fragment.list.NSGatewayListFragmentDirections
import pt.isel.vsddashboardapplication.activities.fragment.parent.NsgPagerFragmentDirections
import pt.isel.vsddashboardapplication.databinding.FragmentNsgatewayBinding
import pt.isel.vsddashboardapplication.model.enumerables.BootstrapStatus
import pt.isel.vsddashboardapplication.utils.RefreshState
import pt.isel.vsddashboardapplication.viewmodel.NSGViewModel
import kotlin.coroutines.CoroutineContext

/**
 * Fragment that handles all information that belongs to the fragment
 */
class NSGatewayFragment : BaseFragment<NSGViewModel, FragmentNsgatewayBinding>(), CoroutineScope, IRefreshableComponent{
    companion object { private const val TAG = "FRAG/NSG" }

    override fun observeViewModel() {
        viewModel.refreshStateLiveData.observe(this, Observer{rf ->
            binding.refreshLayout.isRefreshing = RefreshState.INPROGRESS == rf
        })

        viewModel.nsginfo.observe(this, Observer {
            binding.nsg = it
            changeStatusColor(it?.bootstrapStatus?:BootstrapStatus.INACTIVE)
            binding.statistics.setOnClickListener {view ->
                it?.systemID?.run {
                    val destination = NsgPagerFragmentDirections.actionNsgFragmentToParentSysmonFragment(it.systemID)
                    Navigation.findNavController(view).navigate(destination)
                }
            }
            binding.executePendingBindings()
        })
    }

    override fun setBindingObjects() {
        //updateUI()
        binding.refreshLayout.setOnRefreshListener { refresh() }
        binding.nsg = viewModel.nsginfo.value
        binding.executePendingBindings()
    }

    override fun initViewModel() { }

    override fun assignViewModel(): NSGViewModel =
        (this.parentFragment as NsgPagerFragment).viewModel

    @LayoutRes
    override fun getLayoutRes(): Int = R.layout.fragment_nsgateway

    /**
     * Scope of fragment
     */
    private val job = SupervisorJob()
    override val coroutineContext: CoroutineContext = Dispatchers.Main + job

    /**
     * Updates UI
     *
    private fun updateUI() = launch {
        binding.refreshLayout.setOnRefreshListener { refresh() }
        binding.nsg = viewModel.nsginfo.value
        binding.executePendingBindings()
    }
    */

    /**
     * Change the color of the status
     */
    private fun changeStatusColor(bootstrapStatus: BootstrapStatus?) {
        val color = when(bootstrapStatus){
            BootstrapStatus.ACTIVE -> R.color.green
            else -> R.color.red
        }
        binding.nsgbootstrapActive.setBackgroundColor(color)
    }

    override fun refresh() { viewModel.update() }

}
