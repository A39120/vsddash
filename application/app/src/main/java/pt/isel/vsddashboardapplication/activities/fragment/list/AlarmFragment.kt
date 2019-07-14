package pt.isel.vsddashboardapplication.activities.fragment.list

import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import pt.isel.vsddashboardapplication.activities.NsgActivity
import pt.isel.vsddashboardapplication.activities.adapter.AlarmAdapter
import pt.isel.vsddashboardapplication.activities.fragment.base.BaseListFragment
import pt.isel.vsddashboardapplication.viewmodel.AlarmViewModel

/**
 * Fragment responsible for showing a list of Alarms
 */
class AlarmFragment
    : BaseListFragment<AlarmViewModel>() {

    private lateinit var adapter: AlarmAdapter

    override fun setAdapter() {
        this.adapter = AlarmAdapter()
        observeViewModel()
        binding.list.adapter = this.adapter
    }

    override fun assignViewModel(): AlarmViewModel =
        ViewModelProviders.of(this, viewModelFactory)[AlarmViewModel::class.java]


    override fun observeViewModel() {
        super.observeViewModel()
        viewModel.liveData.observe(this, Observer{
            this.adapter.setList(it)
        })
    }

    override fun initViewModel() {
        val id = (this.activity as NsgActivity).getNsgId()
        viewModel.init(id)
    }


}