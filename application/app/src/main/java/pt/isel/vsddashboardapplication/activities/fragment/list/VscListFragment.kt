package pt.isel.vsddashboardapplication.activities.fragment.list

import android.util.Log
import androidx.lifecycle.Observer
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import pt.isel.vsddashboardapplication.R
import pt.isel.vsddashboardapplication.activities.adapter.VscAdapter
import pt.isel.vsddashboardapplication.activities.fragment.base.BaseFragment
import pt.isel.vsddashboardapplication.activities.fragment.base.IRefreshableComponent
import pt.isel.vsddashboardapplication.activities.fragment.parent.VspParentFragment
import pt.isel.vsddashboardapplication.activities.fragment.parent.VspParentFragmentDirections
import pt.isel.vsddashboardapplication.databinding.FragmentListBinding
import pt.isel.vsddashboardapplication.utils.RefreshState
import pt.isel.vsddashboardapplication.viewmodel.VspViewModel

class VscListFragment : BaseFragment<VspViewModel, FragmentListBinding>(), IRefreshableComponent {
    companion object {
        private const val TAG = "FRAG/VSC_LIST"
    }

    private lateinit var adapter: VscAdapter

    private fun setAdapter() {
        Log.d(TAG, "Setting up adapter")
        adapter = VscAdapter { vsc, view ->
            Log.d(TAG, "Going to VSC: ${vsc.iD}")
            val directions = VspParentFragmentDirections.actionVspParentFragmentToVscParentFragment(vsc.iD)
            Navigation.findNavController(view).navigate(directions)
        }
        binding.list.adapter = adapter
    }

    override fun assignViewModel(): VspViewModel {
        Log.d(TAG, "Setting up view model")
        return (parentFragment as VspParentFragment).viewModel
    }

    override fun initViewModel() { }

    override fun getLayoutRes(): Int = R.layout.fragment_list

    override fun setBindingObjects() {
        Log.d(TAG, "Setting up adapter (${this.javaClass})")
        setAdapter()
        binding.refreshLayout.setOnRefreshListener { refresh() }
        binding.list.layoutManager = LinearLayoutManager(this.context)
    }

    override fun refresh() { viewModel.update() }

    override fun observeViewModel() {
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