package pt.isel.vsddashboardapplication.activities.base

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentPagerAdapter
import pt.isel.vsddashboardapplication.R
import pt.isel.vsddashboardapplication.activities.fragment.base.BaseFragment
import pt.isel.vsddashboardapplication.databinding.FragmentPagerBinding

abstract class BasePagerFragment : Fragment() {
    companion object {
        private const val TAG = "ACT/PAGER"
    }

    private lateinit var binding: FragmentPagerBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        super.onCreateView(inflater, container, savedInstanceState)

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_pager, container, false)

        Log.d(TAG, "Setting up pager adapter")
        val pagerAdapter = getPager()
        binding.viewPager.adapter = pagerAdapter
        binding.tabLayout.setupWithViewPager(binding.viewPager)
        binding.executePendingBindings()

        return binding.root
    }

    abstract fun getPager() : FragmentPagerAdapter

}