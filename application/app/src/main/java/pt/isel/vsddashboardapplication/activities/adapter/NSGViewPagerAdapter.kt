package pt.isel.vsddashboardapplication.activities.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import pt.isel.vsddashboardapplication.activities.NSGatewayFragment
import pt.isel.vsddashboardapplication.activities.NSGatewayStatisticsFragment
import pt.isel.vsddashboardapplication.activities.PortListFragment

class NSGViewPagerAdapter(fm: FragmentManager)
    : FragmentPagerAdapter(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

    private val TITLES = arrayOf("INFO", "STATISTICS", "PORTS")

    override fun getCount(): Int = TITLES.size

    override fun getItem(position: Int): Fragment = when(position){
        0 -> NSGatewayFragment()
        1 -> NSGatewayStatisticsFragment()
        2 -> PortListFragment()
        else -> throw IllegalStateException()
    }

    override fun getPageTitle(position: Int): CharSequence? = TITLES[position]

}
