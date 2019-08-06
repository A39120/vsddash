package pt.isel.vsddashboardapplication.activities.fragment.parent

import androidx.fragment.app.FragmentPagerAdapter
import pt.isel.vsddashboardapplication.activities.adapter.pager.NSGViewPagerAdapter
import pt.isel.vsddashboardapplication.activities.fragment.base.BasePagerFragment

/**
 * NSG Parent fragment, it's the pager fragment responsible for
 * holding fragments that have information on the fragment
 */
class NsgPagerFragment : BasePagerFragment() {

    fun getNsgId() = arguments?.getString("nsgId") ?: ""

    override fun getPager(): FragmentPagerAdapter =
        NSGViewPagerAdapter(this.childFragmentManager)

}