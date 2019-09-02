package pt.isel.vsddashboardapplication.activities.fragment.parent

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import pt.isel.vsddashboardapplication.R
import pt.isel.vsddashboardapplication.activities.base.BaseActivity
import pt.isel.vsddashboardapplication.databinding.ActivityMainBinding

class MainActivity : BaseActivity() {
    companion object {
        private const val TAG = "ACT/MAIN"
    }

    private lateinit var binding : ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        Log.d(TAG, "Main activity created")
        val host = supportFragmentManager
            .findFragmentById(R.id.nav_host_fragment)
            as NavHostFragment? ?: return

        val navController = host.navController
        setSupportActionBar(binding.toolbar)
        NavigationUI.setupActionBarWithNavController(this, navController, binding.drawer)
        navController.addOnDestinationChangedListener{ _, destination, _ ->
            when(destination.id){
                R.id.loginFragment, R.id.apiSettingsFragment, R.id.enterpriseListFragment -> {
                    binding.toolbar.visibility = View.GONE
                }
                else -> {
                    binding.toolbar.visibility = View.VISIBLE
                }
            }
        }
        /*
        val appBarConfiguration = AppBarConfiguration(navController.graph)
        setupActionBar(navController, appBarConfiguration)
        setupNavigationMenu(navController)
        //setupBottomNavMenu(navController)

        navController.addOnDestinationChangedListener { _, destination, _ ->
            when(destination.id){
                // Remove the toolbar from this
                R.id.loginFragment, R.id.apiSettingsFragment, R.id.enterpriseListFragment -> {
                    binding.toolbar.visibility = View.GONE
                }
                else -> {
                    binding.toolbar.visibility = View.VISIBLE
                }
            }


            val dest = try {
                resources.getResourceName(destination.id)
            } catch (ex: Exception){
                destination.id.toString()
            }

            Log.d(TAG, "Navigation added to $destination - ${destination.label}")
        }*/

    }

    var enterpriseId : String? = null

    override fun onSupportNavigateUp(): Boolean =
        NavigationUI.navigateUp(Navigation.findNavController(this, R.id.nav_host_fragment), binding.drawer)

}