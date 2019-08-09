package pt.isel.vsddashboardapplication.activities.fragment.parent

import androidx.fragment.app.FragmentPagerAdapter
import androidx.navigation.fragment.navArgs
import pt.isel.vsddashboardapplication.activities.adapter.pager.NSPortViewPagerAdapter
import pt.isel.vsddashboardapplication.activities.fragment.base.BasePagerFragment

class NSPortPagerFragment : BasePagerFragment() {

    private val args : NSPortPagerFragmentArgs by navArgs()

    override fun getPager(): FragmentPagerAdapter =
        NSPortViewPagerAdapter(this.childFragmentManager)

    fun getNsgId() = args.nsgId
    fun getPortId() = args.portId

}