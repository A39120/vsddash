package pt.isel.vsddashboardapplication.activities

import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.navigation.NavArgs
import androidx.viewpager.widget.ViewPager
import pt.isel.vsddashboardapplication.R
import pt.isel.vsddashboardapplication.activities.adapter.NSGViewPagerAdapter
import pt.isel.vsddashboardapplication.activities.base.BaseActivity
import pt.isel.vsddashboardapplication.databinding.ActivityNsgBinding

class NsgActivity : BaseActivity() {

    private lateinit var binding: ActivityNsgBinding
    private lateinit var mPager : ViewPager
    private lateinit var nsgId: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        nsgId = intent.extras.getString("nsgId","")
        binding = DataBindingUtil.setContentView(this, R.layout.activity_nsg)

        val pagerAdapter = NSGViewPagerAdapter(supportFragmentManager)
        binding.viewPager.adapter = pagerAdapter
        binding.tabLayout.setupWithViewPager(binding.viewPager)
        binding.executePendingBindings()
    }

    override fun onBackPressed() {
        if (mPager.currentItem == 0) { super.onBackPressed() }
        else { mPager.currentItem = mPager.currentItem - 1 }
    }

    fun getNsgId() = nsgId

}
