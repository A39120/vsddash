package pt.isel.vsddashboardapplication.activities.fragment

import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import pt.isel.vsddashboardapplication.activities.NsgActivity
import pt.isel.vsddashboardapplication.activities.adapter.AlarmAdapter
import pt.isel.vsddashboardapplication.repository.AlarmRepository
import pt.isel.vsddashboardapplication.repository.implementation.AlarmRepoImpl
import pt.isel.vsddashboardapplication.viewmodel.AlarmViewModel

/**
 * Fragment responsible for showing a list of Alarms
 */
class AlarmFragment  : BaseListFragment<AlarmViewModel>() {

    private lateinit var adapter: AlarmAdapter
    private val repository : AlarmRepository by lazy { AlarmRepoImpl() }

    override fun setAdapter() {
        this.adapter = AlarmAdapter()
        observeViewModel()
        binding.list.adapter = this.adapter
    }

    override fun assignViewModel(): AlarmViewModel =
        //AlarmViewModel()
        ViewModelProviders.of(this).get(AlarmViewModel::class.java)


    override fun observeViewModel() {
        viewModel.liveData.observe(this, Observer{
            this.adapter.setList(it)
        })
    }

    override fun initViewModel() {
        val id = (this.activity as NsgActivity).getNsgId()
        viewModel.init(repository, id)
    }


}