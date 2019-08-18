package pt.isel.vsddashboardapplication.activities.adapter.pager

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import pt.isel.vsddashboardapplication.activities.fragment.NSGatewayStatisticsFragment
import pt.isel.vsddashboardapplication.activities.fragment.list.AlarmFragment
import pt.isel.vsddashboardapplication.activities.fragment.regular.NSPortFragment
import pt.isel.vsddashboardapplication.activities.fragment.regular.PortStatisticsFragment

class NSPortViewPagerAdapter(fm: FragmentManager)
    : FragmentPagerAdapter(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

    private val TITLES = arrayOf("INFO", "ALARMS")

    override fun getCount(): Int = TITLES.size

    override fun getItem(position: Int): Fragment = when(position){
        0 -> NSPortFragment()
        1 -> AlarmFragment()
        else -> throw IllegalStateException()
    }

    override fun getPageTitle(position: Int): CharSequence? = TITLES[position]

}
