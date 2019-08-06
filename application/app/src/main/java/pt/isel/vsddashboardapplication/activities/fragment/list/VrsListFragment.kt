package pt.isel.vsddashboardapplication.activities.fragment.list

import androidx.lifecycle.ViewModelProviders
import androidx.navigation.Navigation
import pt.isel.vsddashboardapplication.activities.adapter.VrsAdapter
import pt.isel.vsddashboardapplication.activities.fragment.base.BaseListFragment
import pt.isel.vsddashboardapplication.viewmodel.VrsListViewModel

class VrsListFragment : BaseListFragment<VrsListViewModel>() {
    companion object {
        private const val VSC = "VSC_ID"
    }

    private lateinit var adapter : VrsAdapter

    override fun setAdapter() {
        adapter = VrsAdapter { vrs, view ->
            val directions = VrsListFragmentDirections.actionVrsListFragmentToVrsFragment(vrs.iD)
            Navigation.findNavController(view).navigate(directions)
        }
        binding.list.adapter = adapter
        binding.executePendingBindings()
    }

    override fun assignViewModel(): VrsListViewModel =
            ViewModelProviders.of(this, viewModelFactory)[VrsListViewModel::class.java]

    override fun initViewModel() {
        val arg = arguments?.getString(VSC)
        viewModel.init(arg)
    }

}