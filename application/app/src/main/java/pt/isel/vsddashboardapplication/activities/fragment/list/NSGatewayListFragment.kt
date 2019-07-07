package pt.isel.vsddashboardapplication.activities.fragment.list

import android.util.Log
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.Navigation
import pt.isel.vsddashboardapplication.VsdApplication
import pt.isel.vsddashboardapplication.activities.adapter.NSGatewayAdapter
import pt.isel.vsddashboardapplication.viewmodel.AllNSGatewayViewModel

/**
 * The Fragment that displays a list of NSGs;
 */
class NSGatewayListFragment : BaseListFragment<AllNSGatewayViewModel>() {
    companion object {
        private const val TAG = "FRAG/NSGLIST"
    }

    private lateinit var adapter: NSGatewayAdapter

    /**
     * Sets the List of NSG adapter
     */
    override fun setAdapter() {
        Log.d(TAG, "Setting up NSG List adapter")
        adapter = NSGatewayAdapter { nsg, view ->
            val directions = NSGatewayListFragmentDirections.actionNSGatewayListFragmentToNsgActivity(nsg.ID)
            Navigation.findNavController(view).navigate(directions)
        }
        binding.list.adapter = adapter
    }

    override fun assignViewModel(): AllNSGatewayViewModel = ViewModelProviders
        .of(this, viewModelFactory)
        .get(AllNSGatewayViewModel::class.java)

    override fun observeViewModel() {
        Log.d(TAG, "Observing view model")
        viewModel.liveData.observe(this, Observer{
            Log.d(TAG, "Changes have occurred for NSG List ViewModel")
            adapter.setList(it)
        })
    }

    override fun initViewModel() {
        //TODO: Offline mode
        Log.d(TAG, "Setting up session enterprise for the view model")
        val enterprise = (this.activity?.application as VsdApplication).session.getEnterpriseId()
        viewModel.init(enterprise?:"")
    }

}