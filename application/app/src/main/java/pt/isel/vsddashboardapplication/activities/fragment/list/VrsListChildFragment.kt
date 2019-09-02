package pt.isel.vsddashboardapplication.activities.fragment.list

import androidx.lifecycle.Observer
import androidx.navigation.Navigation
import pt.isel.vsddashboardapplication.activities.adapter.VrsAdapter
import pt.isel.vsddashboardapplication.activities.fragment.base.BaseChildListFragment
import pt.isel.vsddashboardapplication.activities.fragment.parent.VscParentFragment
import pt.isel.vsddashboardapplication.activities.fragment.parent.VscParentFragmentDirections
import pt.isel.vsddashboardapplication.utils.RefreshState

class VrsListChildFragment : BaseChildListFragment() {

    override fun observeViewModel() {
        val viewModel = (this.parentFragment as VscParentFragment).viewModel
        viewModel.vrsLiveData.observe(this, Observer {
            adapter.setList(it)
        })

        viewModel.vrsRefreshState.observe(this, Observer {
            binding.refreshLayout.isRefreshing = it == RefreshState.INPROGRESS
        })
    }

    override fun refresh() {
        val viewModel = (this.parentFragment as VscParentFragment).viewModel
        viewModel.updateVrss()
    }

    private lateinit var adapter : VrsAdapter

    override fun setAdapter() {
        adapter = VrsAdapter { vrs, view ->
            val directions = VscParentFragmentDirections.actionVscParentFragmentToVrsParentFragment(vrs.iD)//.actionVrsListFragmentToVrsParentFragment(vrs.iD)
            Navigation.findNavController(view).navigate(directions)
        }
        binding.list.adapter = adapter
        binding.executePendingBindings()
    }

}
