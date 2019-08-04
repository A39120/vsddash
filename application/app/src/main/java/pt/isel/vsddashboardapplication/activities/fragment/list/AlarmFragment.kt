package pt.isel.vsddashboardapplication.activities.fragment.list

import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import pt.isel.vsddashboardapplication.activities.fragment.parent.NsgPagerFragment
import pt.isel.vsddashboardapplication.activities.adapter.AlarmAdapter
import pt.isel.vsddashboardapplication.activities.fragment.base.BaseListFragment
import pt.isel.vsddashboardapplication.viewmodel.NSGAlarmViewModel

/**
 * Fragment responsible for showing a list of Alarms
 */
class AlarmFragment
    : BaseListFragment<NSGAlarmViewModel>() {

    private lateinit var adapter: AlarmAdapter

    override fun setAdapter() {
        this.adapter = AlarmAdapter()
        observeViewModel()
        binding.list.adapter = this.adapter
    }

    override fun assignViewModel(): NSGAlarmViewModel =
        ViewModelProviders.of(this, viewModelFactory)[NSGAlarmViewModel::class.java]


    /**
     * Observes the view model
     */
    override fun observeViewModel() {
        super.observeViewModel()
        viewModel.liveData.observe(this, Observer{
            this.adapter.setList(it)
        })
    }

    /**
     * Initiates the Alarm view model for NSG
     */
    override fun initViewModel() {
        val id = (parentFragment as NsgPagerFragment).getNsgId()
        viewModel.init(id)
    }


}