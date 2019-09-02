package pt.isel.vsddashboardapplication.activities.fragment.regular

import androidx.annotation.LayoutRes
import androidx.lifecycle.Observer
import androidx.navigation.Navigation
import pt.isel.vsddashboardapplication.R
import pt.isel.vsddashboardapplication.activities.fragment.base.BaseChildFragment
import pt.isel.vsddashboardapplication.activities.fragment.parent.NSPortPagerFragment
import pt.isel.vsddashboardapplication.activities.fragment.base.IRefreshableComponent
import pt.isel.vsddashboardapplication.activities.fragment.parent.NSPortPagerFragmentDirections
import pt.isel.vsddashboardapplication.databinding.FragmentNsportBinding
import pt.isel.vsddashboardapplication.utils.RefreshState

/**
 * Fragment that handles all information that belongs to the port
 */
class NSPortFragment : BaseChildFragment<FragmentNsportBinding>(), IRefreshableComponent{

    override fun observeViewModel() {
        val viewModel = (this.parentFragment as NSPortPagerFragment).viewModel
        viewModel.refreshStateLiveData.observe(this, Observer{rf ->
            binding.refreshLayout.isRefreshing = rf == RefreshState.INPROGRESS
        })

        viewModel.liveData.observe(this, Observer {
            binding.nsport = it
            binding.statistics.setOnClickListener { view ->
                val nsg = viewModel.nsgLiveData.value?.name
                val name = it.physicalName
                if(name != null && nsg != null) {
                    val directions = NSPortPagerFragmentDirections
                        .actionNSPortPagerFragmentToPortStatisticsFragment( name, nsg )
                    Navigation.findNavController(view).navigate(directions)
                }
            }
            binding.executePendingBindings()
        })

        viewModel.nsgLiveData.observe(this, Observer {
            binding.nsgname = it.name
            binding.executePendingBindings()
        })

    }

    override fun setBindingObjects() {
        binding.refreshLayout.setOnRefreshListener { refresh() }
        binding.executePendingBindings()
    }

    @LayoutRes
    override fun getLayoutRes(): Int = R.layout.fragment_nsport

    override fun refresh() {
        val viewModel = (this.parentFragment as NSPortPagerFragment).viewModel
        viewModel.update()
    }

}
