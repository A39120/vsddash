package pt.isel.vsddashboardapplication.activities.fragment.list

import android.util.Log
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import pt.isel.vsddashboardapplication.R
import pt.isel.vsddashboardapplication.VsdApplication
import pt.isel.vsddashboardapplication.activities.adapter.EnterpriseAdapter
import pt.isel.vsddashboardapplication.activities.fragment.base.BaseListFragment
import pt.isel.vsddashboardapplication.utils.getAddress
import pt.isel.vsddashboardapplication.utils.getOrganization
import pt.isel.vsddashboardapplication.utils.getUsername
import pt.isel.vsddashboardapplication.utils.sharedPreferences
import pt.isel.vsddashboardapplication.viewmodel.EnterpriseViewModel

class EnterpriseListFragment : BaseListFragment<EnterpriseViewModel>() {
    companion object {
        private const val TAG = "FRAG/ENTERPRISELIST"
        const val ENTERPRISE_ID = "ENTERPRISEID"
    }

    private  val adapter: EnterpriseAdapter = EnterpriseAdapter { enterprise, view ->
        Log.d(TAG, "Clicked on ${enterprise.name} - ${enterprise.iD}")
        val directions = EnterpriseListFragmentDirections.actionEnterpriseListFragmentToMenuFragment(enterprise.iD)
        Navigation.findNavController(view).navigate(directions)
    }

    override fun setAdapter() {
        Log.d(TAG, "Setting up enterprise list adapter")
        adapter.setList(viewModel.liveData.value)
        binding.list.adapter = adapter
    }

    override fun assignViewModel(): EnterpriseViewModel =
        ViewModelProviders.of(this, viewModelFactory)[EnterpriseViewModel::class.java]

    override fun observeViewModel() {
        super.observeViewModel()
        Log.d(TAG, "Observing view model")
        viewModel.liveData.observe(this, Observer {
            Log.d(TAG, "Observer notified (EnterpriseListFragment)")
            (binding.list.adapter as EnterpriseAdapter).setList(it)
            (binding.list.adapter as EnterpriseAdapter).notifyDataSetChanged()
        })
    }

    override fun initViewModel() {
        val userId = this.context?.sharedPreferences()?.getUsername()
        val vsd = this.context?.sharedPreferences()?.getAddress()
        val org = this.context?.sharedPreferences()?.getOrganization()
        Log.d(TAG, "Setting view model with the user id $userId")
        viewModel.init(userId?:"", vsd?: "", org?:"")
    }
}