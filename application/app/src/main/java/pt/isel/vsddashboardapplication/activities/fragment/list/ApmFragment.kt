package pt.isel.vsddashboardapplication.activities.fragment.list

import android.util.Log
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.Navigation
import pt.isel.vsddashboardapplication.R
import pt.isel.vsddashboardapplication.VsdApplication
import pt.isel.vsddashboardapplication.activities.adapter.ApmAdapter
import pt.isel.vsddashboardapplication.activities.fragment.base.BaseListFragment
import pt.isel.vsddashboardapplication.viewmodel.ApmViewModel

class ApmFragment : BaseListFragment<ApmViewModel>(){
    companion object { private const val TAG = "FRAG/APM_LIST" }

    private val adapter : ApmAdapter = ApmAdapter { apm, view ->
        //TODO : Change
        Navigation.findNavController(view).navigate(R.id.action_enterpriseListFragment_to_menuFragment, arguments)
    }

    override fun setAdapter() {
        Log.d(TAG, "Setting up enterprise list adapter")
        adapter.setList(viewModel.liveData.value)
        binding.list.adapter = adapter
    }

    override fun assignViewModel(): ApmViewModel =
        ViewModelProviders.of(this)[ApmViewModel::class.java]

    override fun initViewModel() {
        val enterprise = arguments?.getString("enterpriseId")
        viewModel.init(enterprise?:(this.activity!!.application as VsdApplication).session.getEnterpriseId()!!)
    }

}