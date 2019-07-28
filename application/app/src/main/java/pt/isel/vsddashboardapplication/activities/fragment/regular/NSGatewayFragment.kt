package pt.isel.vsddashboardapplication.activities.fragment.regular

import androidx.lifecycle.ViewModelProviders
import androidx.lifecycle.Observer
import kotlinx.coroutines.*

import pt.isel.vsddashboardapplication.R
import pt.isel.vsddashboardapplication.activities.NsgPagerFragment
import pt.isel.vsddashboardapplication.activities.fragment.base.BaseFragment
import pt.isel.vsddashboardapplication.activities.fragment.base.IRefreshableComponent
import pt.isel.vsddashboardapplication.databinding.FragmentNsgatewayBinding
import pt.isel.vsddashboardapplication.model.enumerables.BootstrapStatus
import pt.isel.vsddashboardapplication.utils.RefreshState
import pt.isel.vsddashboardapplication.viewmodel.NSGInfoViewModel
import kotlin.coroutines.CoroutineContext

/**
 * Fragment that handles all information that belongs to the fragment
 */
class NSGatewayFragment : BaseFragment<NSGInfoViewModel, FragmentNsgatewayBinding>(), CoroutineScope, IRefreshableComponent{

    override fun observeViewModel() {
        viewModel.refreshStateLiveData.observe(this, Observer{rf ->
            binding.refreshLayout.isRefreshing = when(rf){
                RefreshState.INPROGRESS -> true
                else -> false
            }
        })

        viewModel.liveData.observe(this, Observer {
            updateUI()
            changeStatusColor(it.bootstrapStatus)
            binding.executePendingBindings()
        })
    }

    override fun setBindingObjects() { updateUI() }

    override fun initViewModel() {
        val id = (parentFragment as NsgPagerFragment).getNsgId()
        viewModel.init(id)
    }


    override fun assignViewModel(): NSGInfoViewModel =
        ViewModelProviders.of(this, viewModelFactory)[NSGInfoViewModel::class.java]

    override fun getLayoutRes(): Int = R.layout.fragment_nsgateway

    /**
     * Scope of fragment
     */
    private val job = SupervisorJob()
    override val coroutineContext: CoroutineContext = Dispatchers.Main + job

    /**
     * Updates UI
     */
    private fun updateUI() = launch {
        binding.refreshLayout.setOnRefreshListener { refresh() }
        binding.vm = viewModel
        binding.executePendingBindings()
    }

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
