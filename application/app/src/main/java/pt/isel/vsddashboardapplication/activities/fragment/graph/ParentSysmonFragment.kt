package pt.isel.vsddashboardapplication.activities.fragment.graph

import android.util.Log
import androidx.annotation.LayoutRes
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.navArgs
import kotlinx.coroutines.*
import pt.isel.vsddashboardapplication.R
import pt.isel.vsddashboardapplication.activities.fragment.BaseFragment
import pt.isel.vsddashboardapplication.activities.fragment.parent.PortStatisticsParentFragment
import pt.isel.vsddashboardapplication.databinding.FragmentSysmonBinding
import pt.isel.vsddashboardapplication.utils.getDateRange
import pt.isel.vsddashboardapplication.utils.sharedPreferences
import pt.isel.vsddashboardapplication.viewmodel.SysmonViewModel
import java.util.*
import java.util.concurrent.TimeUnit

class ParentSysmonFragment : BaseFragment<SysmonViewModel, FragmentSysmonBinding>() {
    companion object { private const val TAG = "FRAG/SYSMON" }

    private val job = Job()
    private val repeatableScope = CoroutineScope(Dispatchers.IO + job)

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
        val range = context?.sharedPreferences()?.getDateRange() ?: 5
        val calendar = Calendar.getInstance()
        calendar.add(Calendar.MINUTE, -range)
        val from =  calendar.timeInMillis
        viewModel.init(id, from)
    }

    override fun onPause() {
        super.onPause()
        job.cancel()
    }

    override fun onResume() {
        super.onResume()
        Log.d(TAG, "Resuming job")
        initViewModel()
        repeatableScope.launch {
            while(true) {
                delay(30000)
                viewModel.update()
            }
        }
    }
}