package pt.isel.vsddashboardapplication.activities.fragment.parent

import androidx.fragment.app.FragmentPagerAdapter
import pt.isel.vsddashboardapplication.activities.adapter.pager.NSPortViewPagerAdapter
import pt.isel.vsddashboardapplication.activities.fragment.base.BasePagerFragment

class NSPortPagerFragment : BasePagerFragment() {
    companion object {
        private const val TAG = "ACT/NSPORT"

        private const val NSG_ID = "nsgId"
        private const val PORT_ID = "portId"
    }

    override fun getPager(): FragmentPagerAdapter =
        NSPortViewPagerAdapter(this.childFragmentManager)

    fun getNsgId() = arguments?.getString(NSG_ID)
    fun getPortId() = arguments?.getString(PORT_ID)

}