package pt.isel.vsddashboardapplication.activities.fragment.list

import android.util.Log
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import pt.isel.vsddashboardapplication.VsdApplication
import pt.isel.vsddashboardapplication.activities.adapter.EnterpriseAdapter
import pt.isel.vsddashboardapplication.viewmodel.EnterpriseViewModel

class EnterpriseListFragment : BaseListFragment<EnterpriseViewModel>() {
    companion object {
        private const val TAG = "FRAG/ENTERPRISELIST"
    }

    private lateinit var adapter: EnterpriseAdapter

    override fun setAdapter() {
        Log.d(TAG, "Setting up enterprise list adapter")
        adapter = EnterpriseAdapter()
        binding.list.adapter = adapter
    }

    override fun assignViewModel(): EnterpriseViewModel =
        ViewModelProviders.of(this, viewModelFactory)[EnterpriseViewModel::class.java]

    override fun observeViewModel() {
        Log.d(TAG, "Observing view model")
        viewModel.liveData.observe(this, Observer { adapter.setList(it) })
    }

    override fun initViewModel() {
        Log.d(TAG, "Setting view model with the user id")
        val userId = (this.activity?.application as VsdApplication).session.username
        viewModel.init(userId?:"")
    }
}