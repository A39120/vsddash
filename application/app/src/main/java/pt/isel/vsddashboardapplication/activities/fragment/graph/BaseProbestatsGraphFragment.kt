package pt.isel.vsddashboardapplication.activities.fragment.graph

import androidx.annotation.StringRes
import com.jjoe64.graphview.GraphView
import com.jjoe64.graphview.series.DataPoint
import com.jjoe64.graphview.series.LineGraphSeries
import pt.isel.vsddashboardapplication.R
import pt.isel.vsddashboardapplication.activities.fragment.BaseGraphFragment

abstract class BaseProbestatsGraphFragment : BaseGraphFragment() {

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
