package pt.isel.vsddashboardapplication.activities.fragment.list

import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.Navigation
import androidx.navigation.fragment.navArgs
import pt.isel.vsddashboardapplication.activities.adapter.VrsAdapter
import pt.isel.vsddashboardapplication.activities.fragment.base.BaseListFragment
import pt.isel.vsddashboardapplication.activities.fragment.parent.VscParentFragment
import pt.isel.vsddashboardapplication.viewmodel.VrsListViewModel

class VrsListFragment : BaseListFragment<VrsListViewModel>() {

    private lateinit var adapter : VrsAdapter

    override fun setAdapter() {
        adapter = VrsAdapter { vrs, view ->
            val directions = VrsListFragmentDirections.actionVrsListFragmentToVrsParentFragment(vrs.iD)
            Navigation.findNavController(view).navigate(directions)
        }
        binding.list.adapter = adapter
        binding.executePendingBindings()
    }

    override fun assignViewModel(): VrsListViewModel =
        ViewModelProviders.of(this, viewModelFactory)[VrsListViewModel::class.java]

    override fun initViewModel() {
        val arg = (this.parentFragment as? VscParentFragment)?.getVscId() ?:
            navArgs<VrsListFragmentArgs>().value.vscId
        viewModel.init(arg)
    }

    override fun observeViewModel() {
        super.observeViewModel()
        viewModel.liveData.observe(this, Observer {
            adapter.setList(it)
            binding.executePendingBindings()
        })
    }

}