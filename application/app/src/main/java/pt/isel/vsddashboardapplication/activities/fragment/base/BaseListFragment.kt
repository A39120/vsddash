package pt.isel.vsddashboardapplication.activities.fragment.base

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import pt.isel.vsddashboardapplication.utils.RefreshState
import pt.isel.vsddashboardapplication.viewmodel.base.BaseListViewModel
import javax.inject.Inject

/**
 * Base for displaying lists that has a view model attached
 */
abstract class BaseListFragment<T : BaseListViewModel<*>> : BaseChildListFragment(),  IViewModelOwner<T> {
    companion object { private const val TAG = "FRAG/BASE_LIST" }

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    protected val viewModel : T by lazy { assignViewModel() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(TAG, "Creating fragment and initiating View Model (${viewModel.javaClass})")
        initViewModel()
    }

    override fun setBindingObjects() {
        Log.d(TAG, "Setting up adapter (${this.javaClass})")
        setAdapter()
        binding.refreshLayout.setOnRefreshListener { refresh() }
        binding.list.layoutManager = LinearLayoutManager(this.context)
    }

    override fun refresh() { viewModel.update() }

    override fun observeViewModel() {
        viewModel.refreshStateLiveData.observe(this, Observer{rf ->
            Log.d(TAG, "Refresh layout notified $rf (${this.javaClass})")
            binding.refreshLayout.isRefreshing = rf == RefreshState.INPROGRESS
        })
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val root = super.onCreateView(inflater, container, savedInstanceState)
        observeViewModel()
        return root
    }

}
