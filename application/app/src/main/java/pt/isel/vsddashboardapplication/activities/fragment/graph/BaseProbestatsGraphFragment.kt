package pt.isel.vsddashboardapplication.activities.fragment.graph

import android.util.Log
import androidx.annotation.StringRes
import androidx.lifecycle.Observer
import com.jjoe64.graphview.GraphView
import com.jjoe64.graphview.series.DataPoint
import com.jjoe64.graphview.series.LineGraphSeries
import kotlinx.android.synthetic.main.fragment_list.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import pt.isel.vsddashboardapplication.R
import pt.isel.vsddashboardapplication.activities.fragment.BaseGraphFragment
import pt.isel.vsddashboardapplication.model.statistics.DpiProbestats

abstract class BaseProbestatsGraphFragment : BaseGraphFragment() {
    companion object {
        private const val TAG = "GRAPH/PROBESTATS"
        private const val INBOUND = "series_inbound"
        private const val OUTBOUT = "series_outbound"
    }

    protected val inbound = LineGraphSeries<DataPoint>()
    protected val outbound = LineGraphSeries<DataPoint>()

    override fun addSeries(graphView: GraphView) {
        inbound.setAnimated(true)
        outbound.setAnimated(true)

        graphView.addSeries(inbound)
        graphView.addSeries(outbound)
    }

    @StringRes
    override fun getHorizontalTitleResource(): Int = R.string.time

}
