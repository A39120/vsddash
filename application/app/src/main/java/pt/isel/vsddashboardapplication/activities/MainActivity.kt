package pt.isel.vsddashboardapplication.activities

import android.os.Bundle
import android.util.Log
import androidx.databinding.DataBindingUtil
import pt.isel.vsddashboardapplication.activities.base.BaseActivity
import pt.isel.vsddashboardapplication.R
import pt.isel.vsddashboardapplication.VsdApplication
import pt.isel.vsddashboardapplication.databinding.MainActivityBinding

class MainActivity : BaseActivity() {
    companion object {
        private const val TAG = "ACT/MAIN"
    }

    private lateinit var binding : MainActivityBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.main_activity)

        Log.d(TAG, "Main activity created")
    }

}