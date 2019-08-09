package pt.isel.vsddashboardapplication.activities.fragment.parent

import androidx.fragment.app.FragmentPagerAdapter
import androidx.navigation.fragment.navArgs
import pt.isel.vsddashboardapplication.activities.adapter.pager.NSGViewPagerAdapter
import pt.isel.vsddashboardapplication.activities.fragment.base.BasePagerFragment

/**
 * NSG Parent fragment, it's the pager fragment responsible for
 * holding fragments that have information on the fragment
 */
class NsgPagerFragment : BasePagerFragment() {

    private val args : NsgPagerFragmentArgs by navArgs()

    fun getNsgId() = args.nsgId

    override fun getPager(): FragmentPagerAdapter =
        NSGViewPagerAdapter(this.childFragmentManager)

}
