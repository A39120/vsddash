package pt.isel.vsddashboardapplication.activities.fragment.regular

import androidx.lifecycle.Observer
import androidx.navigation.Navigation
import pt.isel.vsddashboardapplication.R
import pt.isel.vsddashboardapplication.activities.fragment.base.BaseChildFragment
import pt.isel.vsddashboardapplication.activities.fragment.parent.VportParentFragment
import pt.isel.vsddashboardapplication.activities.fragment.parent.VportParentFragmentDirections
import pt.isel.vsddashboardapplication.databinding.FragmentVportBinding

class VportFragment : BaseChildFragment<FragmentVportBinding>(){

    override fun getLayoutRes(): Int = R.layout.fragment_vport

    override fun observeViewModel() {
        val viewModel = (this.parentFragment as VportParentFragment)
            .viewModel

        viewModel.liveData.observe(this, Observer{
            binding.vport = it
            binding.executePendingBindings()
        })
    }

    override fun setBindingObjects() {
        val viewModel = (this.parentFragment as VportParentFragment).viewModel
        binding.vport = viewModel.liveData.value
        binding.statistics.setOnClickListener { view ->
            val port = viewModel.liveData.value?.gatewayPortName ?: ""
            val nsg = viewModel.gatewaysLiveData.value?.name ?: ""
            val destination = VportParentFragmentDirections.actionVportParentFragmentToPortStatisticsFragment(nsg = nsg, uplinkName = port)
            Navigation.findNavController(view).navigate(destination)
        }
        binding.executePendingBindings()
    }

}