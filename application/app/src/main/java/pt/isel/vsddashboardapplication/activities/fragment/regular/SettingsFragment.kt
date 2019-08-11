package pt.isel.vsddashboardapplication.activities.fragment.regular

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import dagger.android.support.DaggerFragment
import pt.isel.vsddashboardapplication.R
import pt.isel.vsddashboardapplication.databinding.FragmentSettingsBinding
import pt.isel.vsddashboardapplication.service.EventWorker
import pt.isel.vsddashboardapplication.utils.getVsdAutomaticUpdate
import pt.isel.vsddashboardapplication.utils.setVsdAutomaticUpdate
import pt.isel.vsddashboardapplication.utils.sharedPreferences

class SettingsFragment : Fragment() {

    private lateinit var binding: FragmentSettingsBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_settings, container, false)

        binding.autoUpdateToggle.isChecked = context?.sharedPreferences()?.getVsdAutomaticUpdate() ?: false
        binding.autoUpdateToggle.setOnCheckedChangeListener { buttonView, isChecked ->
            context?.sharedPreferences()?.setVsdAutomaticUpdate(isChecked)
            if(isChecked)
                EventWorker.enqueue()
        }

        return binding.root
    }

}