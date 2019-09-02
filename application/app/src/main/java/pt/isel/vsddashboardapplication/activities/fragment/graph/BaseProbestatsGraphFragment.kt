package pt.isel.vsddashboardapplication.activities.fragment.graph

import android.graphics.Color
import android.util.Log
import androidx.annotation.StringRes
import com.jjoe64.graphview.GraphView
import com.jjoe64.graphview.LegendRenderer
import com.jjoe64.graphview.series.DataPoint
import com.jjoe64.graphview.series.LineGraphSeries
import pt.isel.vsddashboardapplication.R
import pt.isel.vsddashboardapplication.activities.fragment.BaseGraphFragment

abstract class BaseProbestatsGraphFragment : BaseGraphFragment() {
    companion object { private const val TAG = "GRAPH/PROBESTATS" }

    protected val inbound = LineGraphSeries<DataPoint>()
    protected val outbound = LineGraphSeries<DataPoint>()

    override fun addSeries(graphView: GraphView) {
        Log.d(TAG, "Adding inbound and outbound to graph")
        inbound.setAnimated(true)
        outbound.setAnimated(true)

        inbound.color = Color.rgb(150, 50, 50)
        outbound.color =Color.rgb(50, 150, 50)

        inbound.title = resources.getString(R.string.inbound)
        outbound.title = resources.getString(R.string.outbound)

        graphView.legendRenderer.apply {
            isVisible = true
            align = LegendRenderer.LegendAlign.TOP
        }

        graphView.addSeries(inbound)
        graphView.addSeries(outbound)
    }

    @StringRes
    override fun getHorizontalTitleResource(): Int = R.string.time

}
