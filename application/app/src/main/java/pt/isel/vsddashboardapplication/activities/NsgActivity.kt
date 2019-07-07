package pt.isel.vsddashboardapplication.activities

import android.os.Bundle
import android.util.Log
import androidx.databinding.DataBindingUtil
import dagger.android.AndroidInjection
import pt.isel.vsddashboardapplication.R
import pt.isel.vsddashboardapplication.activities.adapter.NSGViewPagerAdapter
import pt.isel.vsddashboardapplication.activities.base.BaseActivity
import pt.isel.vsddashboardapplication.databinding.ActivityNsgBinding

class NsgActivity : BaseActivity() {
    companion object {
        private const val TAG = "ACT/NSG"
    }

    private lateinit var binding: ActivityNsgBinding
    private lateinit var nsgId: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(TAG, "Creating activity")

        nsgId = intent?.extras?.getString("nsgId",null) ?: ""
        binding = DataBindingUtil.setContentView(this, R.layout.activity_nsg)

        Log.d(TAG, "Setting up pager adapter")
        val pagerAdapter = NSGViewPagerAdapter(supportFragmentManager)
        binding.viewPager.adapter = pagerAdapter
        binding.tabLayout.setupWithViewPager(binding.viewPager)
        binding.executePendingBindings()
    }

    override fun onBackPressed() {
        if (binding.viewPager.currentItem == 0) { super.onBackPressed() }
        else { binding.viewPager.currentItem = binding.viewPager.currentItem - 1 }
    }

    fun getNsgId() = nsgId

}
