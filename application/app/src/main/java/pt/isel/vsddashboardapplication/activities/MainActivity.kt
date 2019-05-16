package pt.isel.vsddashboardapplication.activities

import android.os.Bundle
import pt.isel.vsddashboardapplication.activities.base.BaseActivity
import pt.isel.vsddashboardapplication.databinding.MainActivityBinding
import androidx.fragment.app.FragmentManager
import pt.isel.vsddashboardapplication.R
import androidx.fragment.app.Fragment

class MainActivity : BaseActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //binding = DataBindingUtil.setContentView(this, R.layout.main_activity)
        setContentView(R.layout.main_activity)
        //binding.lifecycleOwner = this

        /*addFragmentToActivity(
            this.supportFragmentManager,
            ApiSettingsFragment(),
            R.id.container
        )*/

        addFragmentToActivity(
            this.supportFragmentManager,
            NSGatewayListFragment(),
            R.id.container
        )

    }

    fun addFragmentToActivity(manager: FragmentManager, fragment: Fragment, frameId: Int) {
        manager.beginTransaction().run {
            add(frameId, fragment)
            commit()
        }
    }

}