package pt.isel.vsddashboardapplication.activities.adapter.pager

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import pt.isel.vsddashboardapplication.activities.fragment.list.AlarmFragment
import pt.isel.vsddashboardapplication.activities.fragment.list.VPortListFragment
import pt.isel.vsddashboardapplication.activities.fragment.regular.VrsFragment

/**
 * The VRS View Pager, that has the following children:
 * - Information: information on the VRS;
 * - Alarm: information on the VRS alarms;
 * - VPorts: List of VPorts
 */
class VrsViewPagerAdapter(fm: FragmentManager) : FragmentPagerAdapter(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

    private val TITLES = arrayOf("INFO", "ALARM", "VPORT")

    override fun getCount(): Int = TITLES.size

    override fun getItem(position: Int): Fragment = when(position){
        0 -> VrsFragment()
        1 -> AlarmFragment()
        2 -> VPortListFragment()
        else -> throw IllegalStateException()
    }

    override fun getPageTitle(position: Int): CharSequence? = TITLES[position]

}
