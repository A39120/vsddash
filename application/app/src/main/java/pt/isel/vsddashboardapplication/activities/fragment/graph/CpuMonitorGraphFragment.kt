package pt.isel.vsddashboardapplication.activities.fragment.graph

import android.util.Log
import androidx.annotation.StringRes
import androidx.lifecycle.Observer
import com.jjoe64.graphview.series.DataPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import pt.isel.vsddashboardapplication.R
import java.util.*

class CpuMonitorGraphFragment : BaseMonitorGraphFragment() {
    companion object{
        private const val TAG = "FRAG/SYSMON/CPU"
    }

    @StringRes
    override fun getVerticalTitleResource(): Int = R.string.CPU

    //TODO: Fix the missing livedata
    //TODO: Updater

    override fun observeViewModel() {
        Log.d(TAG, "Observing view model")
        val viewModel = (parentFragment as ParentSysmonFragment).viewModel
        viewModel.liveData.observe(this, Observer { list ->
            Log.d(TAG, "CPU List suffered alterations - ${list.size}")
            val lst = (list.mapNotNull { it.cpu?.let { it1 ->
                    DataPoint(Date(it.timestamp), it1)
                } }
                .sortedBy { it.x }
                ?: listOf<DataPoint>())

            Log.d(TAG, "Appending data - ${lst.size}")
            series.resetData(arrayOf())
            CoroutineScope(Dispatchers.Main).launch {
                appendData(series, lst)
            }
        })
    }


}