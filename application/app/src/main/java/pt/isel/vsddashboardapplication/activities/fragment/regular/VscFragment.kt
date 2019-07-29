package pt.isel.vsddashboardapplication.activities.fragment.regular

import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import pt.isel.vsddashboardapplication.R
import pt.isel.vsddashboardapplication.activities.fragment.base.BaseFragment
import pt.isel.vsddashboardapplication.activities.fragment.base.IRefreshableComponent
import pt.isel.vsddashboardapplication.databinding.FragmentVscBinding
import pt.isel.vsddashboardapplication.utils.RefreshState
import pt.isel.vsddashboardapplication.viewmodel.VscViewModel
import java.lang.IllegalArgumentException

class VscFragment : BaseFragment<VscViewModel, FragmentVscBinding>(), IRefreshableComponent {
    companion object {
        const val VSC_ID = "vsc_id"
    }

    override fun refresh() {
        binding.refreshLayout.setOnRefreshListener { viewModel.update() }
    }

    override fun assignViewModel(): VscViewModel =
        ViewModelProviders.of(this, viewModelFactory)[VscViewModel::class.java]

    override fun getLayoutRes(): Int =
        R.layout.fragment_vsp


    override fun observeViewModel() {
        viewModel.liveData.observe(this, Observer { vsc ->
            binding.vsc = vsc
            binding.executePendingBindings()
        })

        viewModel.refreshStateLiveData.observe(this, Observer{
            binding.refreshLayout.isRefreshing = it == RefreshState.INPROGRESS
        })
    }

    override fun initViewModel() {
        val id = arguments?.getString(VSC_ID)
            ?:throw IllegalArgumentException()
        viewModel.init(id)
    }

    override fun setBindingObjects() {
        binding.vsc = viewModel.liveData.value
        binding.executePendingBindings()
    }
}
