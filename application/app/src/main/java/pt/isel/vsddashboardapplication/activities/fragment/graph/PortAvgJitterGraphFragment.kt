package pt.isel.vsddashboardapplication.activities.fragment.graph

import android.util.Log
import androidx.annotation.StringRes
import pt.isel.vsddashboardapplication.R
import pt.isel.vsddashboardapplication.activities.fragment.parent.PortStatisticsParentFragment

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
