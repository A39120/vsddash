package pt.isel.vsddashboardapplication.activities.fragment.graph

import android.util.Log
import androidx.annotation.StringRes
import pt.isel.vsddashboardapplication.R
import pt.isel.vsddashboardapplication.activities.fragment.parent.PortStatisticsParentFragment

/**
 * Responsible for displaying the inbound and outbound jitter from a port
 * of a NSG
 */
class PortPacketLossGraphFragment : BaseProbestatsGraphFragment() {
    companion object { private const val TAG = "FRAG/PKT_LOSS_GRAPH" }

    override fun observeViewModel() {
        Log.d(TAG, "Observing series")
        val viewModel = (parentFragment as PortStatisticsParentFragment).viewModel
        viewModel.inbound.observe(this, observe(inbound) { it.toPktLossDataPoint() })
        viewModel.outbound.observe(this, observe(outbound) { it.toPktLossDataPoint() })
    }

    @StringRes
    override fun getVerticalTitleResource(): Int = R.string.pkt_loss


}
