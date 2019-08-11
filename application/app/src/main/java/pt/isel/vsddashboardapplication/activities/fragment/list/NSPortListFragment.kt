package pt.isel.vsddashboardapplication.activities.fragment.list

import android.util.Log
import android.view.View
import androidx.lifecycle.Observer
import androidx.navigation.Navigation
import pt.isel.vsddashboardapplication.activities.fragment.parent.NsgPagerFragment
import pt.isel.vsddashboardapplication.activities.adapter.NSPortAdapter
import pt.isel.vsddashboardapplication.activities.fragment.base.BaseChildListFragment
import pt.isel.vsddashboardapplication.activities.fragment.parent.NsgPagerFragmentDirections
import pt.isel.vsddashboardapplication.utils.RefreshState

/**
 * Fragment responsible for setting a list of ports
 */
class NSPortListFragment : BaseChildListFragment() {
    companion object { private const val TAG = "FRAG/PORT_LIST" }

    private lateinit var adapter: NSPortAdapter

    override fun setAdapter() {
        adapter = NSPortAdapter { port, view ->
            Log.d(TAG, "Port chosen - ${port.name} (${port.iD})")
            goToPortFragmentPager(port.iD, view)
        }
        binding.list.adapter = adapter
    }

    override fun refresh() {
        Log.d(TAG, "Refreshing the NSPorts (${this.javaClass})")
        val viewModel = (this.parentFragment as NsgPagerFragment).viewModel
        viewModel.updateNsgPorts()
    }

    override fun observeViewModel() {
        val viewModel = (this.parentFragment as NsgPagerFragment).viewModel

        viewModel.refreshStateLiveData.observe(this, Observer{
            binding.refreshLayout.isRefreshing = it == RefreshState.INPROGRESS
        })

        viewModel.portsLiveData.observe(this, Observer{
            Log.d(TAG, "Setting up new list for Port List")
            adapter.setList(it)
            adapter.notifyDataSetChanged()
        })
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
