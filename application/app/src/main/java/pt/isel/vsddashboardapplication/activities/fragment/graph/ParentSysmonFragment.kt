package pt.isel.vsddashboardapplication.activities.fragment.graph

import android.util.Log
import androidx.annotation.LayoutRes
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.navArgs
import pt.isel.vsddashboardapplication.R
import pt.isel.vsddashboardapplication.activities.fragment.BaseFragment
import pt.isel.vsddashboardapplication.databinding.FragmentSysmonBinding
import pt.isel.vsddashboardapplication.viewmodel.SysmonViewModel

class ParentSysmonFragment : BaseFragment<SysmonViewModel, FragmentSysmonBinding>() {
    companion object { private const val TAG = "FRAG/SYSMON" }

    @LayoutRes
    override fun getLayoutRes(): Int = R.layout.fragment_sysmon

    private val arguments : ParentSysmonFragmentArgs by navArgs()

    override fun observeViewModel(){}
    override fun setBindingObjects(){}

    override fun assignViewModel(): SysmonViewModel =
        ViewModelProviders.of(this, viewModelFactory)[SysmonViewModel::class.java]

    override fun initViewModel() {
        val id = arguments.systemId
        Log.d(TAG, "Initiating the view model for id $id")
        viewModel.init(systemId = id)
    }


}