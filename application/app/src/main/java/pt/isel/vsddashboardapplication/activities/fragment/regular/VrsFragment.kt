package pt.isel.vsddashboardapplication.activities.fragment.regular

import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import pt.isel.vsddashboardapplication.R
import pt.isel.vsddashboardapplication.activities.fragment.base.BaseChildFragment
import pt.isel.vsddashboardapplication.activities.fragment.base.BaseFragment
import pt.isel.vsddashboardapplication.activities.fragment.parent.VrsParentFragment
import pt.isel.vsddashboardapplication.activities.fragment.parent.VscParentFragment
import pt.isel.vsddashboardapplication.databinding.FragmentVrsBinding
import pt.isel.vsddashboardapplication.utils.RefreshState
import pt.isel.vsddashboardapplication.viewmodel.VrsViewModel
import java.lang.IllegalArgumentException

class VrsFragment : BaseChildFragment<FragmentVrsBinding>(){

    override fun getLayoutRes(): Int = R.layout.fragment_vrs

    override fun observeViewModel() {
        val viewModel = (parentFragment as VrsParentFragment).viewModel
        viewModel.liveData.observe(this, Observer {
            binding.vrs = it
            binding.executePendingBindings()
        })

        viewModel.refreshStateLiveData.observe(this, Observer {
            binding.refreshLayout.isRefreshing = it == RefreshState.INPROGRESS
        })
    }

    override fun setBindingObjects() {
        val viewModel = (parentFragment as VrsParentFragment).viewModel
        binding.vrs = viewModel.liveData.value
        binding.executePendingBindings()
    }

}