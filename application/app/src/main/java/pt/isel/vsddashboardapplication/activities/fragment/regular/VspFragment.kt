package pt.isel.vsddashboardapplication.activities.fragment.regular

import androidx.lifecycle.Observer
import pt.isel.vsddashboardapplication.R
import pt.isel.vsddashboardapplication.activities.fragment.base.BaseChildFragment
import pt.isel.vsddashboardapplication.activities.fragment.base.IRefreshableComponent
import pt.isel.vsddashboardapplication.activities.fragment.parent.VspParentFragment
import pt.isel.vsddashboardapplication.databinding.FragmentVspBinding
import pt.isel.vsddashboardapplication.utils.RefreshState

/**
 * A fragment dedicated to VSP
 */
class VspFragment : BaseChildFragment<FragmentVspBinding>(), IRefreshableComponent {

    override fun refresh() {
        val viewModel = (this.parentFragment as VspParentFragment).viewModel
        viewModel.update()
    }

    override fun getLayoutRes(): Int = R.layout.fragment_vsp

    override fun observeViewModel() {
        val viewModel = (this.parentFragment as VspParentFragment).viewModel
        viewModel.liveData.observe(this, Observer { vsp ->
            binding.vsp = vsp
            binding.executePendingBindings()
        })

        viewModel.refreshStateLiveData.observe(this, Observer {
            binding.refreshLayout.isRefreshing = it == RefreshState.INPROGRESS
        })
    }


    override fun setBindingObjects() {
        val viewModel = (this.parentFragment as VspParentFragment).viewModel
        binding.refreshLayout.setOnRefreshListener { refresh() }
        binding.vsp = viewModel.liveData.value
        binding.executePendingBindings()
    }

}