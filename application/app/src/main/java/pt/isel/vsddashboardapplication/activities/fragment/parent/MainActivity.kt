package pt.isel.vsddashboardapplication.activities.fragment.parent

import android.os.Bundle
import android.util.Log
import androidx.databinding.DataBindingUtil
import pt.isel.vsddashboardapplication.activities.base.BaseActivity
import pt.isel.vsddashboardapplication.R
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
    }

}