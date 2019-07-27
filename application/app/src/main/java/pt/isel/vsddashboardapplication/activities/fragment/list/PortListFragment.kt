package pt.isel.vsddashboardapplication.activities.fragment.list

import android.util.Log
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.Navigation
import pt.isel.vsddashboardapplication.activities.NsgPagerFragment
import pt.isel.vsddashboardapplication.activities.NsgPagerFragmentDirections
import pt.isel.vsddashboardapplication.activities.adapter.NSPortAdapter
import pt.isel.vsddashboardapplication.activities.fragment.base.BaseListFragment
import pt.isel.vsddashboardapplication.viewmodel.PortListViewModel

/**
 * Fragment responsible for setting a list of ports
 */
class PortListFragment : BaseListFragment<PortListViewModel>() {
    companion object {
        private const val TAG = "FRAG/PORT_LIST"
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
        val nsgId = (parentFragment as NsgPagerFragment).getNsgId()
        viewModel.init(nsgId)
    }

    override fun assignViewModel(): PortListViewModel =
        ViewModelProviders.of(this, viewModelFactory)[PortListViewModel::class.java]

    private lateinit var adapter: NSPortAdapter

    override fun setAdapter() {
        adapter = NSPortAdapter { port, view ->
            Log.d(TAG, "Port chosen - ${port.name} (${port.iD})")
            goToPortFragmentPager(port.iD, view)
        }
        binding.list.adapter = adapter
    }

    /**
     * Goes to port pager fragment
     * @param portId: the ID of the port that the next fragment will focus
     * @param view: the view  that contains the NavController
     */
    private fun goToPortFragmentPager(portId: String, view: View){
        (parentFragment as NsgPagerFragment).run {
            val directions = NsgPagerFragmentDirections.actionNsgFragmentToNSPortPagerFragment(
                nsgId = getNsgId(),
                portId = portId
            )

            Navigation.findNavController(view).navigate(directions)
        }
    }

}
