package pt.isel.vsddashboardapplication.activities.fragment.base

import android.util.Log
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import pt.isel.vsddashboardapplication.R
import pt.isel.vsddashboardapplication.databinding.FragmentListBinding
import pt.isel.vsddashboardapplication.utils.RefreshState
import pt.isel.vsddashboardapplication.viewmodel.base.BaseListViewModel

/**
 * Base for displaying lists
 */
abstract class BaseListFragment<T : BaseListViewModel<*>> : BaseFragment<T, FragmentListBinding>(), IRefreshableComponent {
    companion object {
        private const val TAG = "FRAG/BASELIST"
    }

    override fun getLayoutRes(): Int = R.layout.fragment_list

    protected abstract fun setAdapter()

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
    }

}
