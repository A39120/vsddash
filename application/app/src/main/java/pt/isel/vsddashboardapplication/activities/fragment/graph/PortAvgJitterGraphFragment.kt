package pt.isel.vsddashboardapplication.activities.fragment.graph

import android.util.Log
import androidx.annotation.StringRes
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.jjoe64.graphview.GraphView
import com.jjoe64.graphview.Viewport
import com.jjoe64.graphview.series.DataPoint
import com.jjoe64.graphview.series.LineGraphSeries
import kotlinx.coroutines.*
import pt.isel.vsddashboardapplication.R
import pt.isel.vsddashboardapplication.activities.fragment.BaseGraphFragment
import pt.isel.vsddashboardapplication.activities.fragment.parent.PortStatisticsParentFragment
import pt.isel.vsddashboardapplication.activities.fragment.regular.PortStatisticsFragment
import pt.isel.vsddashboardapplication.model.statistics.DpiProbestats
import pt.isel.vsddashboardapplication.utils.TimeRangeCalculator
import pt.isel.vsddashboardapplication.viewmodel.ProbestatsViewModel
import java.lang.IllegalArgumentException
import java.util.*

/**
 * Responsible for displaying the inbound and outbound jitter from a port
 * of a NSG
 */
class PortAvgJitterGraphFragment : BaseProbestatsGraphFragment() {
    companion object { private const val TAG = "FRAG/JITTER_GRAPH" }

    override fun observeViewModel() {
        Log.d(TAG, "Observing series")
        val viewModel = (parentFragment as PortStatisticsParentFragment).viewModel
        viewModel.inbound.observe(this, observe(inbound) { it.toJitterDataPoint() })
        viewModel.outbound.observe(this, observe(outbound) { it.toJitterDataPoint() })
    }

    @StringRes
    override fun getVerticalTitleResource(): Int = R.string.avg_jitter


}
