package pt.isel.vsddashboardapplication.activities.fragment.list

import android.util.Log
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import pt.isel.vsddashboardapplication.activities.NsgActivity
import pt.isel.vsddashboardapplication.activities.adapter.NSPortAdapter
import pt.isel.vsddashboardapplication.activities.fragment.base.BaseListFragment
import pt.isel.vsddashboardapplication.viewmodel.PortListViewModel

/**
 * Fragment responsible for setting a list of ports
 */
class PortListFragment : BaseListFragment<PortListViewModel>() {
    companion object {
        private const val TAG = "FRAG/PORTLIST"
    }

    override fun observeViewModel() {
        super.observeViewModel()
        viewModel.liveData.observe(this, Observer{
            Log.d(TAG, "Setting up new list for Port List")
            adapter.setList(it)
        })
    }

    override fun initViewModel() {
        Log.d(TAG, "Getting NSG ID passed from activity")
        val nsgId = (this.activity as NsgActivity).getNsgId()
        viewModel.init(nsgId)
    }

    override fun assignViewModel(): PortListViewModel =
        ViewModelProviders.of(this, viewModelFactory)[PortListViewModel::class.java]

    private lateinit var adapter: NSPortAdapter

    override fun setAdapter() {
        adapter = NSPortAdapter { _, _ ->}
        binding.list.adapter = adapter
    }

    override fun refresh() {
    }

}
