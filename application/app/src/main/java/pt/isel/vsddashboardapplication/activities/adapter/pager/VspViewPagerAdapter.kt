package pt.isel.vsddashboardapplication.activities.adapter.pager

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import pt.isel.vsddashboardapplication.activities.fragment.list.VscListFragment
import pt.isel.vsddashboardapplication.activities.fragment.regular.VspFragment

class VspViewPagerAdapter(fm: FragmentManager)
    : FragmentPagerAdapter(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

    private val TITLES = arrayOf("INFO", "VSC")

    override fun getCount(): Int = TITLES.size

    override fun getItem(position: Int): Fragment = when(position){
        0 -> VspFragment()
        1 -> VscListFragment()
        else -> throw IllegalStateException()
    }

    override fun getPageTitle(position: Int): CharSequence? = TITLES[position]

}
