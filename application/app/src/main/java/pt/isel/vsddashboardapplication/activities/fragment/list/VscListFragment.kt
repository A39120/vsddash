package pt.isel.vsddashboardapplication.activities.fragment.list

import android.util.Log
import androidx.lifecycle.Observer
import androidx.navigation.Navigation
import pt.isel.vsddashboardapplication.R
import pt.isel.vsddashboardapplication.activities.adapter.VscAdapter
import pt.isel.vsddashboardapplication.activities.fragment.base.BaseChildListFragment
import pt.isel.vsddashboardapplication.activities.fragment.parent.VspParentFragment
import pt.isel.vsddashboardapplication.activities.fragment.parent.VspParentFragmentDirections
import pt.isel.vsddashboardapplication.utils.RefreshState

class VscListFragment : BaseChildListFragment() {
    companion object { private const val TAG = "FRAG/VSC_LIST" }

    private lateinit var adapter: VscAdapter

    override fun setAdapter() {
        Log.d(TAG, "Setting up adapter")
        adapter = VscAdapter { vsc, view ->
            Log.d(TAG, "Going to VSC: ${vsc.iD}")
            val directions = VspParentFragmentDirections.actionVspParentFragmentToVscParentFragment(vsc.iD)
            Navigation.findNavController(view).navigate(directions)
        }
        binding.list.adapter = adapter
    }

    override fun refresh() {
        val viewModel = (this.parentFragment as VspParentFragment).viewModel
        viewModel.updateVscLiveData()
    }

    override fun getLayoutRes(): Int = R.layout.fragment_list

    override fun observeViewModel() {
        val viewModel = (this.parentFragment as VspParentFragment).viewModel
        viewModel.refreshStateLiveData.observe(this, Observer{rf ->
            binding.refreshLayout.isRefreshing = when(rf){
                RefreshState.INPROGRESS -> true
                else -> false
            }
        })

        viewModel.vscListLiveData.observe(this, Observer {
            adapter.setList(it)
            binding.executePendingBindings()
        })
    }

}