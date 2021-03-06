package pt.isel.vsddashboardapplication.activities.fragment.list

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.StringRes
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.Navigation
import pt.isel.vsddashboardapplication.R
import pt.isel.vsddashboardapplication.activities.adapter.EnterpriseAdapter
import pt.isel.vsddashboardapplication.activities.fragment.base.BaseListFragment
import pt.isel.vsddashboardapplication.activities.fragment.parent.MainActivity
import pt.isel.vsddashboardapplication.utils.getAddress
import pt.isel.vsddashboardapplication.utils.getOrganization
import pt.isel.vsddashboardapplication.utils.getUsername
import pt.isel.vsddashboardapplication.utils.sharedPreferences
import pt.isel.vsddashboardapplication.viewmodel.EnterpriseViewModel

class EnterpriseListFragment : BaseListFragment<EnterpriseViewModel>() {
    companion object { private const val TAG = "FRAG/ENTERPRISE_LIST" }

    private  val adapter: EnterpriseAdapter = EnterpriseAdapter { enterprise, view ->
        Log.d(TAG, "Clicked on ${enterprise.name} - ${enterprise.iD}")
        val directions = EnterpriseListFragmentDirections.actionEnterpriseListFragmentToMenuFragment(enterprise.iD)
        (this.activity as MainActivity).enterpriseId = enterprise.iD
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

    @StringRes
    override fun getTitle() = R.string.enterprise_list
}