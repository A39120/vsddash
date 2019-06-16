package pt.isel.vsddashboardapplication.activities.fragment

import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import pt.isel.vsddashboardapplication.activities.NsgActivity
import pt.isel.vsddashboardapplication.activities.adapter.AlarmAdapter
import pt.isel.vsddashboardapplication.repository.AlarmRepository
import pt.isel.vsddashboardapplication.repository.implementation.AlarmRepoImpl
import pt.isel.vsddashboardapplication.viewmodel.AlarmViewModel

class AlarmFragment  : BaseListFragment<AlarmViewModel>() {

    private lateinit var adapter: AlarmAdapter
    private val repository : AlarmRepository by lazy { AlarmRepoImpl() }

    override fun setAdapter() {
        adapter = AlarmAdapter()
        binding.list.adapter = adapter
        adapter.setList(viewModel.liveData.value)
    }

    override fun assignViewModel(): AlarmViewModel =
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