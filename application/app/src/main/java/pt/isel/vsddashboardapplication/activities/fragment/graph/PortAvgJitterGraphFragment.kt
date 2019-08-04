package pt.isel.vsddashboardapplication.activities.fragment.graph

import android.util.Log
import androidx.annotation.StringRes
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.jjoe64.graphview.GraphView
import com.jjoe64.graphview.Viewport
import kotlinx.coroutines.*
import pt.isel.vsddashboardapplication.R
import pt.isel.vsddashboardapplication.activities.fragment.base.BaseGraphFragment
import pt.isel.vsddashboardapplication.activities.fragment.regular.PortStatisticsFragment
import pt.isel.vsddashboardapplication.model.statistics.DpiProbestats
import pt.isel.vsddashboardapplication.utils.SeriesContainer
import pt.isel.vsddashboardapplication.utils.TimeRangeCalculator
import pt.isel.vsddashboardapplication.viewmodel.ProbestatsViewModel

/**
 * Responsible for displaying the inbound and outbound jitter from a port
 * of a NSG
 */
class PortAvgJitterGraphFragment : BaseGraphFragment<ProbestatsViewModel>() {
    companion object {
        private const val TAG = "FRAG/JITTER_GRAPH"
        private const val INBOUND = "inbound"
        private const val OUTBOUND = "outbound"
    }

    private var range = TimeRangeCalculator.getLastDayRange()

    /**
     * Sets the viewport for the fragment
     * @param viewport: the viewport of the fragment
     */
    override fun setViewport(viewport: Viewport) {
        viewport.apply {
            isScalable = false

            setMinY(0.0)
            setMaxY(1.0)

            binding.graph.viewport.apply {
                setMinX(range.start.toDouble())
                setMaxX(range.end.toDouble())
            }

            isXAxisBoundsManual = true
            isYAxisBoundsManual = false
            binding.executePendingBindings()
        }
    }

    //private var inboundGraphSeries = LineGraphSeries<DataPoint>()
    //private var outboundGraphSeries = LineGraphSeries<DataPoint>()

    private val seriesContainer = SeriesContainer()

    override fun getMaxData(): Int = DEFAULT_MAX

    override fun assignViewModel(): ProbestatsViewModel =
        ViewModelProviders.of(this, viewModelFactory)[ProbestatsViewModel::class.java]

    override fun observeViewModel() {
        viewModel.inbound.observe(this, getObserver(INBOUND))
        viewModel.outbound.observe(this, getObserver(OUTBOUND))

        /*
        viewModel.inbound.observe(this, Observer { stats ->
            val values = stats
                .map { probe ->
                    val date = Date(probe.timestamp)
                    val jitter = probe.avgJitter
                    return@map if (jitter != null) DataPoint(date, jitter) else null
                }
                .sortedBy { it?.x }
                .filterNotNull()

            val maxY = values.maxBy { it.y }?.y

            if (maxY == null || maxY < 1.0)
                binding.graph.viewport.setMaxY(1.0)
            else
                binding.graph.viewport.setMaxY(maxY)

            binding.graph.viewport.apply {
                val min = values.minBy { it.x }
                setMinX(min?.x?:0.0)
                val max = values.maxBy { it.x }
                setMaxX(max?.x?:1.0)
            }

            binding.executePendingBindings()

            CoroutineScope(Dispatchers.Main).launch {
                inboundGraphSeries.resetData(arrayOf())
                values.forEach {
                    inboundGraphSeries.appendData(it, true, 2 * values.size)
                }
            }

            //binding.graph.addSeries(inboundGraphSeries)
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
        */

        CoroutineScope(Dispatchers.IO).launch {
            delay(5000L)
            val range = TimeRangeCalculator.getLastDayRange()
            viewModel.setBoundaries(range.start, range.end)
            viewModel.updateLiveData()
        }

    }

    override fun initViewModel() {
        val portId = (parentFragment as PortStatisticsFragment).getPortId()?:""
        val nsgId = (parentFragment as PortStatisticsFragment).getNsgId()?:""
        Log.d(TAG, "Initiating View Model with port $portId and NSG $nsgId")
        viewModel.init(portId, nsgId)
    }

    @StringRes
    override fun getHorizontalTitleResource(): Int = R.string.avg_jitter

    @StringRes
    override fun getVerticalTitleResource(): Int = R.string.time

    override fun addSeries(graphView: GraphView) {
        Log.d(TAG, "Adding series - Inbound & Outbound")
        seriesContainer.add(INBOUND, listOf())
        seriesContainer.add(OUTBOUND, listOf())

        /*
        inboundGraphSeries.setAnimated(true)
        graphView.addSeries(inboundGraphSeries)
        outboundGraphSeries.setAnimated(true)
        graphView.addSeries(outboundGraphSeries)
        */

        graphView.addSeries(seriesContainer.get(INBOUND))
        graphView.addSeries(seriesContainer.get(OUTBOUND))
    }

    private fun setNewBoundaries(min: Long, max: Long) {
        viewModel.setBoundaries(min, max)
        //inboundGraphSeries.resetData(arrayOf())
        //outboundGraphSeries.resetData(arrayOf())
    }

    private fun getObserver(key: String) : Observer<List<DpiProbestats>>{
        return Observer { stats ->
            val dpoints = stats.mapNotNull { it.toJitterDataPoint() }
                .sortedBy { it.x }

            seriesContainer.add(key, dpoints)
            binding.executePendingBindings()
        }
    }

}