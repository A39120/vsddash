package pt.isel.vsddashboardapplication.activities.fragment.regular

import androidx.lifecycle.Observer
import androidx.navigation.Navigation
import pt.isel.vsddashboardapplication.R
import pt.isel.vsddashboardapplication.activities.fragment.base.BaseChildFragment
import pt.isel.vsddashboardapplication.activities.fragment.parent.VrsParentFragment
import pt.isel.vsddashboardapplication.activities.fragment.parent.VrsParentFragmentDirections
import pt.isel.vsddashboardapplication.databinding.FragmentVrsBinding
import pt.isel.vsddashboardapplication.utils.RefreshState

class VrsFragment : BaseChildFragment<FragmentVrsBinding>(){

    override fun getLayoutRes(): Int = R.layout.fragment_vrs

    override fun observeViewModel() {
        val viewModel = (parentFragment as VrsParentFragment).viewModel
        viewModel.liveData.observe(this, Observer {vrs ->
            binding.vrs = vrs
            binding.statistics.setOnClickListener {
                vrs.description?.let { desc ->
                    val destinations = VrsParentFragmentDirections.actionVrsParentFragmentToParentSysmonFragment(desc)
                    Navigation.findNavController(it).navigate(destinations)
                }
            }
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