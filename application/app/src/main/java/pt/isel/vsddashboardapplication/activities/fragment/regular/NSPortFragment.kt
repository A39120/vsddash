package pt.isel.vsddashboardapplication.activities.fragment.regular

import androidx.annotation.LayoutRes
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import pt.isel.vsddashboardapplication.R
import pt.isel.vsddashboardapplication.activities.fragment.parent.NSPortPagerFragment
import pt.isel.vsddashboardapplication.activities.fragment.base.BaseFragment
import pt.isel.vsddashboardapplication.activities.fragment.base.IRefreshableComponent
import pt.isel.vsddashboardapplication.databinding.FragmentNsportBinding
import pt.isel.vsddashboardapplication.utils.RefreshState
import pt.isel.vsddashboardapplication.viewmodel.NSPortViewModel

/**
 * Fragment that handles all information that belongs to the port
 */
class NSPortFragment : BaseFragment<NSPortViewModel, FragmentNsportBinding>(), IRefreshableComponent {

    override fun observeViewModel() {
        viewModel.refreshStateLiveData.observe(this, Observer{rf ->
            binding.refreshLayout.isRefreshing = when(rf){
                RefreshState.INPROGRESS -> true
                else -> false
            }
        })
    }

    override fun initViewModel() {
        val portId = (parentFragment as NSPortPagerFragment).getPortId()
        val nsgId = (parentFragment as NSPortPagerFragment).getNsgId()
        viewModel.init(portId, nsgId)
    }

    override fun setBindingObjects() {
        binding.refreshLayout.setOnRefreshListener { refresh() }
        binding.vm = viewModel
        binding.executePendingBindings()
    }

    override fun assignViewModel(): NSPortViewModel =
        ViewModelProviders.of(this, viewModelFactory)[NSPortViewModel::class.java]


    @LayoutRes
    override fun getLayoutRes(): Int = R.layout.fragment_nsport

    override fun refresh() { viewModel.update() }



}
