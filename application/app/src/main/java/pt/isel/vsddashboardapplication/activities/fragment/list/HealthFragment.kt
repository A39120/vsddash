package pt.isel.vsddashboardapplication.activities.fragment.list

import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import pt.isel.vsddashboardapplication.R
import pt.isel.vsddashboardapplication.activities.adapter.HealthAdapter
import pt.isel.vsddashboardapplication.activities.fragment.base.BaseListFragment
import pt.isel.vsddashboardapplication.viewmodel.HealthViewModel

class HealthFragment : BaseListFragment<HealthViewModel>() {

    override fun getTitle(): Int = R.string.health

    private val adapter by lazy { HealthAdapter() }
    override fun setAdapter() {
        adapter.setList(viewModel.liveData.value)
        binding.list.adapter = adapter
        binding.executePendingBindings()
    }

    override fun assignViewModel(): HealthViewModel =
        ViewModelProviders.of(this, viewModelFactory)[HealthViewModel::class.java]

    override fun initViewModel() { viewModel.init() }

    override fun observeViewModel() {
        viewModel.liveData.observe(this, Observer{list ->
            adapter.setList(list)
            binding.executePendingBindings()
        })
        super.observeViewModel()
    }

}