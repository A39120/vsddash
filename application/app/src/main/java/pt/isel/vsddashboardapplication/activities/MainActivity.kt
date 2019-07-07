package pt.isel.vsddashboardapplication.activities

import android.os.Bundle
import android.util.Log
import pt.isel.vsddashboardapplication.activities.base.BaseActivity
import pt.isel.vsddashboardapplication.R
import pt.isel.vsddashboardapplication.VsdApplication

class MainActivity : BaseActivity() {
    companion object {
        private const val TAG = "ACT/MAIN"
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)
        Log.d(TAG, "Main activity created")
    }

}