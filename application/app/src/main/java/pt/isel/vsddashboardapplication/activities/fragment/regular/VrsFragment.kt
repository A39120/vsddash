package pt.isel.vsddashboardapplication.activities.fragment.regular

import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import pt.isel.vsddashboardapplication.R
import pt.isel.vsddashboardapplication.activities.fragment.base.BaseFragment
import pt.isel.vsddashboardapplication.activities.fragment.parent.VscParentFragment
import pt.isel.vsddashboardapplication.databinding.FragmentVrsBinding
import pt.isel.vsddashboardapplication.utils.RefreshState
import pt.isel.vsddashboardapplication.viewmodel.VrsViewModel
import java.lang.IllegalArgumentException

class VrsFragment : BaseFragment<VrsViewModel, FragmentVrsBinding>(){
    companion object {
        const val VRS_ID = "vrsId"
    }

    override fun assignViewModel(): VrsViewModel =
        ViewModelProviders.of(this, viewModelFactory)[VrsViewModel::class.java]


    override fun getLayoutRes(): Int = R.layout.fragment_vrs


    override fun observeViewModel() {
        viewModel.liveData.observe(this, Observer {
            binding.vrs = it
            binding.executePendingBindings()
        })

        viewModel.refreshStateLiveData.observe(this, Observer {
            binding.refreshLayout.isRefreshing = it == RefreshState.INPROGRESS
        })

    }

    override fun initViewModel() {
        val id = (this.parentFragment as? VscParentFragment)?.getVscId()
            ?: arguments?.getString(VRS_ID)
            ?: throw IllegalArgumentException()

        viewModel.init(id)
    }

    override fun setBindingObjects() {
        binding.vrs = viewModel.liveData.value
        binding.executePendingBindings()
    }

}