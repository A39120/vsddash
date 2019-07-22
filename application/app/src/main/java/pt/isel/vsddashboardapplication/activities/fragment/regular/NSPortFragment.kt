package pt.isel.vsddashboardapplication.activities.fragment.regular

import androidx.annotation.LayoutRes
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import pt.isel.vsddashboardapplication.R
import pt.isel.vsddashboardapplication.activities.NSPortActivity
import pt.isel.vsddashboardapplication.activities.fragment.base.BaseFragment
import pt.isel.vsddashboardapplication.activities.fragment.base.IRefreshableComponent
import pt.isel.vsddashboardapplication.databinding.NsportFragmentBinding
import pt.isel.vsddashboardapplication.utils.RefreshState
import pt.isel.vsddashboardapplication.viewmodel.NSPortViewModel

/**
 * Fragment that handles all information that belongs to the port
 */
class NSPortFragment : BaseFragment<NSPortViewModel, NsportFragmentBinding>(), IRefreshableComponent {

    override fun observeViewModel() {
        viewModel.refreshStateLiveData.observe(this, Observer{rf ->
            binding.refreshLayout.isRefreshing = when(rf){
                RefreshState.INPROGRESS -> true
                RefreshState.NONE -> false
            }
        })
    }

    override fun initViewModel() {
        val portId = (this.activity as NSPortActivity).getPortId()
        val nsgId = (this.activity as NSPortActivity).getNsgId()
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
    override fun getLayoutRes(): Int = R.layout.nsport_fragment

    override fun refresh() { viewModel.update() }



}
