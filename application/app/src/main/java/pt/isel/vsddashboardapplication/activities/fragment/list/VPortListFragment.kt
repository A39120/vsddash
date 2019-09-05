package pt.isel.vsddashboardapplication.activities.fragment.list

import androidx.lifecycle.Observer
import androidx.navigation.Navigation
import pt.isel.vsddashboardapplication.activities.adapter.VportAdapter
import pt.isel.vsddashboardapplication.activities.fragment.base.BaseChildListFragment
import pt.isel.vsddashboardapplication.activities.fragment.parent.NsgPagerFragmentDirections
import pt.isel.vsddashboardapplication.activities.fragment.parent.VportParentFragment
import pt.isel.vsddashboardapplication.activities.fragment.parent.VrsParentFragment
import pt.isel.vsddashboardapplication.activities.fragment.parent.VrsParentFragmentDirections
import pt.isel.vsddashboardapplication.utils.RefreshState

class VPortListFragment : BaseChildListFragment() {

    private lateinit var adapter: VportAdapter

    override fun setAdapter() {
        adapter = VportAdapter{ vport, view ->
            val vrsId = (parentFragment as VrsParentFragment).getVrsId()
            val directions = VrsParentFragmentDirections
                .actionVrsParentFragmentToVportParentFragment(vport.iD, vrsId)
            Navigation.findNavController(view).navigate(directions)
        }
        binding.list.adapter = adapter
        binding.executePendingBindings()
    }

    override fun observeViewModel() {
        val viewModel = (this.parentFragment as VrsParentFragment).viewModel
        viewModel.vportLiveData.observe(this, Observer {
            adapter.setList(it)
            binding.executePendingBindings()
        })

        viewModel.refreshStateLiveData.observe(this, Observer {
            binding.refreshLayout.isRefreshing = it == RefreshState.INPROGRESS
        })

    }

    override fun refresh() {
        (this.parentFragment as VrsParentFragment)
            .viewModel
            .updateVportLiveData()
    }

}