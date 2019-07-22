package pt.isel.vsddashboardapplication.activities

import android.os.Bundle
import androidx.fragment.app.FragmentPagerAdapter
import pt.isel.vsddashboardapplication.R
import pt.isel.vsddashboardapplication.activities.adapter.NSGViewPagerAdapter
import pt.isel.vsddashboardapplication.activities.base.BasePagerActivity

class NsgActivity : BasePagerActivity() {
    companion object {
        private const val TAG = "ACT/NSG"
    }

    private lateinit var nsgId: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        nsgId = intent?.extras?.getString("nsgId",null) ?: ""
    }

    fun getNsgId() = nsgId

    override fun getPager(): FragmentPagerAdapter =
        NSGViewPagerAdapter(this.supportFragmentManager)

}
