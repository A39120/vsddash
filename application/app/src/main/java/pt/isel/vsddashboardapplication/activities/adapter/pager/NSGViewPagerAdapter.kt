package pt.isel.vsddashboardapplication.activities.adapter.pager

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import pt.isel.vsddashboardapplication.activities.fragment.list.AlarmFragment
import pt.isel.vsddashboardapplication.activities.fragment.regular.NSGatewayFragment
import pt.isel.vsddashboardapplication.activities.fragment.NSGatewayStatisticsFragment
import pt.isel.vsddashboardapplication.activities.fragment.list.PortListFragment

class NSGViewPagerAdapter(fm: FragmentManager)
    : FragmentPagerAdapter(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

    private val TITLES = arrayOf("INFO", "STATISTICS", "ALARMS", "PORTS")

    override fun getCount(): Int = TITLES.size

    override fun getItem(position: Int): Fragment = when(position){
        0 -> NSGatewayFragment()
        1 -> NSGatewayStatisticsFragment()
        2 -> AlarmFragment()
        3 -> PortListFragment()
        else -> throw IllegalStateException()
    }

    override fun getPageTitle(position: Int): CharSequence? = TITLES[position]

}