package pt.isel.vsddashboardapplication.activities.fragment.parent

import android.os.Bundle
import android.os.PersistableBundle
import dagger.android.DaggerActivity
import pt.isel.vsddashboardapplication.R

class LoadingActivity : DaggerActivity() {

    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)
        setContentView(R.layout.activity_loading)
    }

}