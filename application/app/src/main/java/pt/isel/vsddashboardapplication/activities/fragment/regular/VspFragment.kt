package pt.isel.vsddashboardapplication.activities.fragment.regular

import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import pt.isel.vsddashboardapplication.R
import pt.isel.vsddashboardapplication.activities.fragment.base.BaseFragment
import pt.isel.vsddashboardapplication.activities.fragment.base.IRefreshableComponent
import pt.isel.vsddashboardapplication.databinding.FragmentVspBinding
import pt.isel.vsddashboardapplication.viewmodel.VspViewModel

class VspFragment : BaseFragment<VspViewModel, FragmentVspBinding>(), IRefreshableComponent{

    override fun refresh() {
        //TODO
    }

    override fun assignViewModel(): VspViewModel =
        ViewModelProviders.of(this, viewModelFactory)[VspViewModel::class.java]

    override fun getLayoutRes(): Int =
        R.layout.fragment_vsp


    override fun observeViewModel() {
        viewModel.liveData.observe(this, Observer { vsp ->
            binding.vsp = vsp
            binding.executePendingBindings()
        })

    }

    override fun initViewModel() { }

    override fun setBindingObjects() { }
}