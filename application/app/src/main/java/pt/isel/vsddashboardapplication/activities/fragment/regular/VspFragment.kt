package pt.isel.vsddashboardapplication.activities.fragment.regular

import androidx.lifecycle.Observer
import pt.isel.vsddashboardapplication.R
import pt.isel.vsddashboardapplication.activities.fragment.base.BaseFragment
import pt.isel.vsddashboardapplication.activities.fragment.base.IRefreshableComponent
import pt.isel.vsddashboardapplication.activities.fragment.parent.VspParentFragment
import pt.isel.vsddashboardapplication.databinding.FragmentVspBinding
import pt.isel.vsddashboardapplication.utils.RefreshState
import pt.isel.vsddashboardapplication.viewmodel.VspViewModel

/**
 * A fragment dedicated to VSP
 */
class VspFragment : BaseFragment<VspViewModel, FragmentVspBinding>(), IRefreshableComponent {

    override fun refresh() { viewModel.update() }

    override fun assignViewModel(): VspViewModel =
        (parentFragment as VspParentFragment).viewModel

    override fun getLayoutRes(): Int = R.layout.fragment_vsp

    override fun observeViewModel() {
        viewModel.liveData.observe(this, Observer { vsp ->
            binding.vsp = viewModel
            binding.executePendingBindings()
        })

        viewModel.refreshStateLiveData.observe(this, Observer {
            binding.refreshLayout.isRefreshing = it == RefreshState.INPROGRESS
        })
    }

    override fun initViewModel() {
        viewModel.init()
    }

    override fun setBindingObjects() {
        binding.vsp = viewModel
        binding.executePendingBindings()
    }

}