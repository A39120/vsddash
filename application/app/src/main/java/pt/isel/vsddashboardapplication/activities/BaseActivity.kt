package pt.isel.vsddashboardapplication.activities

import android.app.Activity
import android.view.Menu
import android.view.MenuInflater
import pt.isel.vsddashboardapplication.R

abstract class BaseActivity : Activity() {

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val menuInflater = MenuInflater(this)
        menuInflater.inflate(R.menu.menu, menu)
        return true
    }



}