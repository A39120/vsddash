package pt.isel.vsddashboardapplication.activities.base

import android.os.Bundle
import android.util.Log
import androidx.annotation.NavigationRes
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.FragmentPagerAdapter
import pt.isel.vsddashboardapplication.R
import pt.isel.vsddashboardapplication.databinding.ActivityPagerBinding

abstract class BasePagerActivity : BaseActivity() {
    companion object {
        private const val TAG = "ACT/PAGER"
    }

    private lateinit var binding: ActivityPagerBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_pager)

        Log.d(TAG, "Setting up pager adapter")
        val pagerAdapter = getPager()
        binding.viewPager.adapter = pagerAdapter
        binding.tabLayout.setupWithViewPager(binding.viewPager)
        binding.executePendingBindings()
    }

    override fun onBackPressed() {
        if (binding.viewPager.currentItem == 0) { super.onBackPressed() }
        else { binding.viewPager.currentItem = binding.viewPager.currentItem - 1 }
    }

    abstract fun getPager() : FragmentPagerAdapter

}