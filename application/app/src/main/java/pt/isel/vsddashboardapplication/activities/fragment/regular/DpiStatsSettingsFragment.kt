package pt.isel.vsddashboardapplication.activities.fragment.regular

import android.widget.ArrayAdapter
import androidx.annotation.LayoutRes
import androidx.core.view.isEmpty
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.NavOptions
import androidx.navigation.Navigation
import androidx.navigation.Navigator
import androidx.navigation.fragment.navArgs
import pt.isel.vsddashboardapplication.R
import pt.isel.vsddashboardapplication.activities.fragment.BaseFragment
import pt.isel.vsddashboardapplication.databinding.FragmentDpiSettingsBinding
import pt.isel.vsddashboardapplication.utils.*
import pt.isel.vsddashboardapplication.viewmodel.MonitorViewModel

class DpiStatsSettingsFragment : BaseFragment<MonitorViewModel, FragmentDpiSettingsBinding>() {

    private val arguments : DpiStatsSettingsFragmentArgs by navArgs()

    @LayoutRes
    override fun getLayoutRes(): Int = R.layout.fragment_dpi_settings

    override fun observeViewModel() {
        // Setup APM
        viewModel.liveData.observe(this, Observer {apms ->
            val list = ArrayList<String?>()
            val currentApm = context?.sharedPreferences()?.getApm()?:""
            list.add("")
            if(!apms.isNullOrEmpty())
                list.addAll(apms.map { it.name })
            val adapter = ArrayAdapter(this.context!!,
                R.layout.support_simple_spinner_dropdown_item,
                list)
            binding.apmSpinner.adapter = adapter
            if(list.contains(currentApm))
                binding.apmSpinner.setSelection(list.indexOf(currentApm))
        })

        // Setup Performance Monitor
        viewModel.perfMonitorsLiveData.observe(this, Observer { pfs ->
            val list = ArrayList<String?>()
            val currentPf = context?.sharedPreferences()?.getPerfMonitor()?:""
            list.add("")
            if(!pfs.isNullOrEmpty())
                list.addAll(pfs.map { it.name })
            val adapter = ArrayAdapter(this.context!!,
                R.layout.support_simple_spinner_dropdown_item,
                list)
            binding.perfmonitorSpinner.adapter = adapter
            if(list.contains(currentPf))
                binding.apmSpinner.setSelection(list.indexOf(currentPf))
        })
    }

    override fun setBindingObjects() {
        binding.minutes = context?.sharedPreferences()?.getDateRange() ?: 5
        binding.saveButton.setOnClickListener{
            val pf = binding.perfmonitorSpinner.selectedItem as String?
            val apm = binding.apmSpinner.selectedItem as String?
            val minutes = Integer.parseInt(binding.range.text.toString())
            context?.sharedPreferences()?.setApm(apm?:"")
            context?.sharedPreferences()?.setPerfMonitor(pf?:"")
            context?.sharedPreferences()?.setDateRange(minutes?:5)
            Navigation.findNavController(it).popBackStack()
        }
    }

    override fun assignViewModel(): MonitorViewModel =
        ViewModelProviders.of(this, viewModelFactory)[MonitorViewModel::class.java]

    override fun initViewModel() {
        val enterpriseId = arguments.enterpriseId
        viewModel.init(enterpriseId)
    }
}