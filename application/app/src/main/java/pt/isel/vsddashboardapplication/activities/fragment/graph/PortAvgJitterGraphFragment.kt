package pt.isel.vsddashboardapplication.activities.fragment.graph

import androidx.annotation.StringRes
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.jjoe64.graphview.GraphView
import com.jjoe64.graphview.Viewport
import com.jjoe64.graphview.series.DataPoint
import com.jjoe64.graphview.series.LineGraphSeries
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import pt.isel.vsddashboardapplication.R
import pt.isel.vsddashboardapplication.activities.NSPortActivity
import pt.isel.vsddashboardapplication.viewmodel.ProbestatsViewModel
import java.util.*
import kotlin.collections.ArrayList

/**
 * Responsible for displaying the inbound and outbound jitter from a port
 * of a NSG
 */
class PortAvgJitterGraphFragment : BaseGraphFragment<ProbestatsViewModel>() {
    companion object {
        private const val TAG = "FRAG/JITTER_GRAPH"
    }

    private val MIN_Y = 0.0
    private val MIN_X: Double = getMinX()


    private val MAX_Y = 1.0
    private val MAX_X = (System.currentTimeMillis().toDouble() * 2)

    /**
     * Sets the viewport for the fragment
     * @param viewport: the viewport of the fragment
     */
    override fun setViewport(viewport: Viewport) {
        viewport.apply {
            isScalable = true
            isScrollable = true
            setMinX(MIN_X)
            setMaxX(MAX_X)

            setMaxY(MAX_Y)
            setMinY(MIN_Y)

            isXAxisBoundsManual = true
            isYAxisBoundsManual = false
        }
    }

    private val inboundList = ArrayList<DataPoint>()
    private val outboundList = ArrayList<DataPoint>()

    private val inboundGraphSeries = LineGraphSeries<DataPoint>()
    private val outboundGraphSeries = LineGraphSeries<DataPoint>()

    override fun getMaxData(): Int = DEFAULT_MAX

    override fun assignViewModel(): ProbestatsViewModel =
        ViewModelProviders.of(this, viewModelFactory)[ProbestatsViewModel::class.java]

    override fun observeViewModel() {
        viewModel.inbound.observe(this, Observer { stats ->
            val list = stats.mapNotNull { probe ->
                    val date = Date(probe.timestamp)
                    probe.avgJitter?.let { DataPoint(date, it) }
                }.filter { dp -> inboundList.contains(dp) }

            inboundList.addAll(list)
            for (dataPoint in list)
                CoroutineScope(Dispatchers.Main).launch {
                    appendData(inboundGraphSeries, dataPoint)
                }

        })

        viewModel.outbound.observe(this, Observer { stats ->
            val list = stats.mapNotNull { probe ->
                val date = Date(probe.timestamp)
                probe.avgJitter?.let { DataPoint(date, it) }
            }
                .filter { dp -> inboundList.contains(dp) }

            outboundList.addAll(list)
            for (dataPoint in list)
                CoroutineScope(Dispatchers.Main).launch {
                    appendData(outboundGraphSeries, dataPoint)
                }
        })

        CoroutineScope(Dispatchers.IO).launch {
            delay(5000L)
            viewModel.updateLiveData()
        }

    }

    override fun initViewModel() {
        val portId = (this.activity as NSPortActivity).getPortId()
        val nsgId = (this.activity as NSPortActivity).getNsgId()
        viewModel.init(portId, nsgId)
    }

    @StringRes
    override fun getHorizontalTitleResource(): Int = R.string.avg_jitter

    @StringRes
    override fun getVerticalTitleResource(): Int = R.string.time

    private fun getMinX(): Double {
        val calendar = Calendar.getInstance()
        calendar.set(Calendar.YEAR, 2012)
        return calendar.timeInMillis.toDouble()
    }

    override fun addSeries(graphView: GraphView) {
        graphView.addSeries(inboundGraphSeries)
        graphView.addSeries(outboundGraphSeries)

    }

}