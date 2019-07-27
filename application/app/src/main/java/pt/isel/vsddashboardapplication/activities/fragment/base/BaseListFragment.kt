package pt.isel.vsddashboardapplication.activities.fragment.base

import android.util.Log
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.list_fragment.view.*
import pt.isel.vsddashboardapplication.R
import pt.isel.vsddashboardapplication.databinding.ListFragmentBinding
import pt.isel.vsddashboardapplication.utils.RefreshState
import pt.isel.vsddashboardapplication.viewmodel.base.BaseListViewModel

/**
 * Base for displaying lists
 */
abstract class BaseListFragment<T : BaseListViewModel<*>> : BaseFragment<T, ListFragmentBinding>(), IRefreshableComponent {
    companion object {
        private const val TAG = "FRAG/BASELIST"
    }

    override fun getLayoutRes(): Int = R.layout.list_fragment

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
