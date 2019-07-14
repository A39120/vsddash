package pt.isel.vsddashboardapplication.activities.base

import android.os.Bundle
import android.os.PersistableBundle
import androidx.fragment.app.FragmentActivity
import dagger.android.AndroidInjection
import dagger.android.support.DaggerAppCompatActivity

/**
 * Base activity that will be extended from all other activities
 */
abstract class BaseActivity : DaggerAppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)
        AndroidInjection.inject(this)
    }

}