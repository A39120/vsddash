package pt.isel.vsddashboardapplication.activities.fragment.regular

import androidx.annotation.LayoutRes
import androidx.lifecycle.Observer
import androidx.navigation.Navigation
import pt.isel.vsddashboardapplication.R
import pt.isel.vsddashboardapplication.activities.fragment.base.BaseChildFragment
import pt.isel.vsddashboardapplication.activities.fragment.base.IRefreshableComponent
import pt.isel.vsddashboardapplication.activities.fragment.parent.VscParentFragment
import pt.isel.vsddashboardapplication.activities.fragment.parent.VscParentFragmentDirections
import pt.isel.vsddashboardapplication.databinding.FragmentVscBinding
import pt.isel.vsddashboardapplication.utils.RefreshState

/**
 * Contains details of the VSC
 */
class VscFragment : BaseChildFragment<FragmentVscBinding>(), IRefreshableComponent {

    override fun refresh() {
        val viewModel = (parentFragment as VscParentFragment).viewModel
        binding.refreshLayout.setOnRefreshListener {
            viewModel.update()
        }
    }

    @LayoutRes
    override fun getLayoutRes(): Int = R.layout.fragment_vsc

    override fun observeViewModel() {
        val viewModel = (parentFragment as VscParentFragment).viewModel
        viewModel.liveData.observe(this, Observer { vsc ->
            binding.vsc = vsc
            binding.statistics.setOnClickListener { view ->
                vsc?.name?.run {
                    val direction = VscParentFragmentDirections.actionVscParentFragmentToParentSysmonFragment(this)
                    Navigation.findNavController(view).navigate(direction)
                }
            }

            binding.executePendingBindings()
        })

        viewModel.refreshStateLiveData.observe(this, Observer{
            binding.refreshLayout.isRefreshing = it == RefreshState.INPROGRESS
        })
    }

    override fun setBindingObjects() {
        val viewModel = (parentFragment as VscParentFragment).viewModel
        binding.vsc = viewModel.liveData.value
        binding.executePendingBindings()
    }

}
