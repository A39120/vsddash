package pt.isel.vsddashboardapplication.activities.fragment.regular

import androidx.lifecycle.ViewModelProviders
import androidx.lifecycle.Observer
import kotlinx.coroutines.*

import pt.isel.vsddashboardapplication.R
import pt.isel.vsddashboardapplication.activities.NsgActivity
import pt.isel.vsddashboardapplication.activities.fragment.base.BaseFragment
import pt.isel.vsddashboardapplication.activities.fragment.base.IRefreshableComponent
import pt.isel.vsddashboardapplication.databinding.NsgatewayFragmentBinding
import pt.isel.vsddashboardapplication.model.enumerables.BootstrapStatus
import pt.isel.vsddashboardapplication.viewmodel.NSGViewModel
import kotlin.coroutines.CoroutineContext

/**
 * Fragment that handles all information that belongs to the fragment
 */
class NSGatewayFragment : BaseFragment<NSGViewModel, NsgatewayFragmentBinding>(), CoroutineScope, IRefreshableComponent{

    override fun observeViewModel() {
        viewModel.liveData.observe(this, Observer {
            updateUI()
            changeStatusColor(it.bootstrapStatus)
            binding.executePendingBindings()
        })
    }

    override fun setBindingObjects() { updateUI() }

    override fun initViewModel() {
        val id = (this.activity as NsgActivity).getNsgId()
        viewModel.init(id)
    }


    override fun assignViewModel(): NSGViewModel =
        ViewModelProviders.of(this, viewModelFactory)[NSGViewModel::class.java]

    override fun getLayoutRes(): Int = R.layout.nsgateway_fragment

    /**
     * Scope of fragment
     */
    private val job = SupervisorJob()
    override val coroutineContext: CoroutineContext = Dispatchers.Main + job

    /**
     * Updates UI
     */
    private fun updateUI() = launch {
        binding.vm = viewModel
        binding.executePendingBindings()
    }

    private suspend fun update() {
        withContext(Dispatchers.IO) { viewModel.update() }
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


    override fun refresh() { }

}
