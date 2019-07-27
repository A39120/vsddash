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
import pt.isel.vsddashboardapplication.activities.NSPortPagerFragment
import pt.isel.vsddashboardapplication.utils.TimeRangeCalculator
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
    private val MAX_Y = 3.0

    /**
     * Sets the viewport for the fragment
     * @param viewport: the viewport of the fragment
     */
    override fun setViewport(viewport: Viewport) {
        val range = TimeRangeCalculator.getLastDayRange()
        viewport.apply {
            isScalable = true
            isScrollable = false

            setMinX(range.start.toDouble())
            setMaxX(range.end.toDouble())

            setMaxY(MAX_Y)
            setMinY(MIN_Y)

            isXAxisBoundsManual = true
            isYAxisBoundsManual = false
        }
    }

    private val inboundList = ArrayList<DataPoint>()
    private val outboundList = ArrayList<DataPoint>()

    private var inboundGraphSeries = LineGraphSeries<DataPoint>()
    private var outboundGraphSeries = LineGraphSeries<DataPoint>()

    override fun getMaxData(): Int = DEFAULT_MAX

    override fun assignViewModel(): ProbestatsViewModel =
        ViewModelProviders.of(this, viewModelFactory)[ProbestatsViewModel::class.java]

    override fun observeViewModel() {
        viewModel.inbound.observe(this, Observer { stats ->
            val values = stats
                .map { probe ->
                    val date = Date(probe.timestamp)
                    val jitter = probe.avgJitter
                    return@map if (jitter != null) DataPoint(date, jitter) else null
                }
                .sortedBy { it?.x }
                .filterNotNull()

            binding.graph.removeSeries(inboundGraphSeries)
            inboundGraphSeries = LineGraphSeries(values.toTypedArray())
            binding.graph.addSeries(inboundGraphSeries)
        })

        viewModel.outbound.observe(this, Observer { stats ->
            val values = stats
                .map { probe ->
                    val date = Date(probe.timestamp)
                    val jitter = probe.avgJitter
                    return@map if (jitter != null) DataPoint(date, jitter) else null
                }
                .sortedBy { it?.x }
                .filterNotNull()

            binding.graph.removeSeries(outboundGraphSeries)
            outboundGraphSeries = LineGraphSeries(values.toTypedArray())
            binding.graph.addSeries(outboundGraphSeries)
        })

        CoroutineScope(Dispatchers.IO).launch {
            delay(5000L)
            val range = TimeRangeCalculator.getLastDayRange()
            viewModel.setBoundaries(range.start, range.end)
            viewModel.updateLiveData()
        }

    }

    override fun initViewModel() {
        val portId = (parentFragment as NSPortPagerFragment).getPortId()?:""
        val nsgId = (parentFragment as NSPortPagerFragment).getNsgId()?:""
        viewModel.init(portId, nsgId)
    }

    @StringRes
    override fun getHorizontalTitleResource(): Int = R.string.avg_jitter

    @StringRes
    override fun getVerticalTitleResource(): Int = R.string.time

    private fun getMinX(): Double {
        /*val calendar = Calendar.getInstance()
        calendar.set(Calendar.YEAR, 2012)
        return calendar.timeInMillis.toDouble()
        */
        return (System.currentTimeMillis() - 60 * 1000).toDouble()
    }

    override fun addSeries(graphView: GraphView) {
        graphView.addSeries(inboundGraphSeries)
        graphView.addSeries(outboundGraphSeries)

    }

}