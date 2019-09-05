package pt.isel.vsddashboardapplication.activities.fragment.graph

import androidx.annotation.StringRes
import com.jjoe64.graphview.GraphView
import com.jjoe64.graphview.Viewport
import com.jjoe64.graphview.series.DataPoint
import com.jjoe64.graphview.series.LineGraphSeries
import pt.isel.vsddashboardapplication.R
import pt.isel.vsddashboardapplication.activities.fragment.BaseGraphFragment

abstract class BaseMonitorGraphFragment : BaseGraphFragment() {

    protected val series = LineGraphSeries<DataPoint>()

    override fun addSeries(graphView: GraphView) {
        series.setAnimated(true)
        graphView.addSeries(series)
    }

    @StringRes
    override fun getHorizontalTitleResource(): Int = R.string.time

}