package pt.isel.vsddashboardapplication.activities.fragment.list

import android.util.Log
import android.view.View
import androidx.annotation.StringRes
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.Navigation
import androidx.navigation.fragment.navArgs
import pt.isel.vsddashboardapplication.R
import pt.isel.vsddashboardapplication.VsdApplication
import pt.isel.vsddashboardapplication.activities.adapter.NSGatewayAdapter
import pt.isel.vsddashboardapplication.activities.fragment.base.BaseListFragment
import pt.isel.vsddashboardapplication.viewmodel.AllNSGatewayViewModel

/**
 * The Fragment that displays a list of NSGs;
 */
class NSGatewayListFragment : BaseListFragment<AllNSGatewayViewModel>() {
    companion object { private const val TAG = "FRAG/NSG_LIST" }

    private lateinit var adapter: NSGatewayAdapter
    private val args : NSGatewayListFragmentArgs by navArgs()

    /**
     * Sets the List of NSG adapter
     */
    override fun setAdapter() {
        Log.d(TAG, "Setting up NSG List adapter")
        adapter = NSGatewayAdapter { nsg, view -> navigateToNSG(nsg.ID, view) }
        binding.list.adapter = adapter
    }

    override fun assignViewModel(): AllNSGatewayViewModel = ViewModelProviders
        .of(this, viewModelFactory)
        .get(AllNSGatewayViewModel::class.java)

    override fun observeViewModel() {
        super.observeViewModel()
        Log.d(TAG, "Observing view model")
        viewModel.liveData.observe(this, Observer{
            Log.d(TAG, "Changes have occurred for NSG List ViewModel")
            adapter.setList(it)
        })
    }

    override fun initViewModel() {
        Log.d(TAG, "Setting up session enterprise for the view model")
        val enterprise = args.enterpriseId
        viewModel.init(enterprise)
    }

    private fun navigateToNSG(nsgId: String, view: View){
        Log.d(TAG, "Navigating to NSG ($nsgId)")
        val directions = NSGatewayListFragmentDirections.actionNSGatewayListFragmentToNsgFragment(nsgId)
        Navigation.findNavController(view).navigate(directions)
    }

    @StringRes
    override fun getTitle(): Int = R.string.nsg_list

}