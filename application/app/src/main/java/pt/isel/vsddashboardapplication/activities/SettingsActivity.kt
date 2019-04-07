package pt.isel.vsddashboardapplication.activities

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import pt.isel.vsddashboardapplication.R
import android.preference.PreferenceManager
import android.content.SharedPreferences
import androidx.databinding.DataBindingUtil
import pt.isel.vsddashboardapplication.utils.SharedPreferenceKeys
import pt.isel.vsddashboardapplication.databinding.ActivitySettingsBinding


class SettingsActivity : Activity() {
    companion object {
        const val tag = "SETTINGSACT/"

        fun start(context: Context){
            val intent = Intent(context, SettingsActivity::class.java)
            context.startActivity(intent)
        }
    }

    private lateinit var mBinding : ActivitySettingsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_settings)

        val prefs = PreferenceManager.getDefaultSharedPreferences(this)
        val address = prefs.getString(SharedPreferenceKeys.CURRENTADDRESS, SharedPreferenceKeys.DEFAULTADDRESS)

        mBinding.setVsdaddress(address)
    }
}