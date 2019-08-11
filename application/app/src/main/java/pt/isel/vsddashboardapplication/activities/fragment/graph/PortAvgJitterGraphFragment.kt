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
import pt.isel.vsddashboardapplication.activities.fragment.base.BaseGraphFragment
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
class PortAvgJitterGraphFragment : BaseGraphFragment<ProbestatsViewModel>() {
    companion object {
        private const val TAG = "FRAG/JITTER_GRAPH"
        private const val INBOUND_IDX = 0
        private const val OUTBOUND_IDX = 1
    }

    private var range = TimeRangeCalculator.getLastDayRange()
    private val inbound = LineGraphSeries<DataPoint>()
    private val outbound = LineGraphSeries<DataPoint>()

    private var minX = -1.0
    private var maxX = 0.0
    private var maxY = 1.0

    /**
     * Sets the viewport for the fragment
     * @param viewport: the viewport of the fragment
     */
    override fun setViewport(viewport: Viewport) {
        viewport.apply {
            isScalable = true

            setMinY(0.0)
            setMaxY(maxY)

            setMinX(minX)
            setMaxX(maxX)

            isXAxisBoundsManual = true
            isYAxisBoundsManual = true
            binding.executePendingBindings()
        }

    }

    override fun getMaxData(): Int = DEFAULT_MAX

    override fun assignViewModel(): ProbestatsViewModel =
        ViewModelProviders.of(this, viewModelFactory)[ProbestatsViewModel::class.java]

    override fun observeViewModel() {
        viewModel.inbound.observe(this, getObserver(INBOUND_IDX))
        viewModel.outbound.observe(this, getObserver(OUTBOUND_IDX))

        /*
        viewModel.inbound.getAlarmLiveData(this, Observer { stats ->
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

        viewModel.outbound.getAlarmLiveData(this, Observer { stats ->
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
            val range = TimeRangeCalculator.getLastDayRange()
            viewModel.setBoundaries(range.start, range.end)
            viewModel.updateLiveData()
        }

    }

    override fun initViewModel() {
        val portId = (parentFragment as PortStatisticsFragment).getPortId()
        val nsgId = (parentFragment as PortStatisticsFragment).getNsgId()
        Log.d(TAG, "Initiating View Model with port $portId and NSG $nsgId")
        viewModel.init(portId, nsgId)
    }

    @StringRes
    override fun getHorizontalTitleResource(): Int =  R.string.time

    @StringRes
    override fun getVerticalTitleResource(): Int = R.string.avg_jitter

    override fun addSeries(graphView: GraphView) {
        Log.d(TAG, "Adding series - Inbound & Outbound")

        inbound.setAnimated(true)
        outbound.setAnimated(true)

        graphView.addSeries(inbound)
        graphView.addSeries(outbound)
    }

    private fun setNewBoundaries(min: Long, max: Long) {
        viewModel.setBoundaries(min, max)
    }

    /**
     * Returns the observer of the series
     * @param key: the series title
     * @return observer that looks for alterations in List
     */
    private fun getObserver(key: Int) : Observer<List<DpiProbestats>>{
        return Observer { stats ->
            val dpoints = stats
                .mapNotNull { it.toJitterDataPoint() }
                .sortedBy { it.x }
                .toTypedArray()

            if(dpoints.isEmpty())
                return@Observer

            Log.d(TAG, "Appending data points")
            binding.graph.viewport.apply {

                val currMinX = dpoints.minBy { it.x }!!.x
                if(currMinX < minX || minX < 0.0) {
                    Log.d(TAG, "Setting new max y: ${Date(currMinX.toLong())}")
                    minX = currMinX
                    setMinX(minX)
                }

                val currMaxX = dpoints.maxBy { it.x }!!.x
                if(currMaxX > maxX) {
                    Log.d(TAG, "Setting new max x: ${Date(currMaxX.toLong())}")
                    maxX = currMaxX
                    setMaxX(maxX)
                }

                val currMaxY = dpoints.maxBy { it.y }!!.y
                if(currMaxY > maxY) {
                    Log.d(TAG, "Setting new max y: ${Date(currMaxY.toLong())}")
                    maxY = currMaxY
                    setMaxY(maxY)
                }
            }

            Log.d(TAG, "Resetting ${if(key == 0) "inbound" else "outbound"}")
            getSeries(key).resetData(dpoints)

            /*
            for(point in dpoints) {
                Log.d(TAG, "Appending data point - x: ${point.x}, y: ${point.y}")

                if(point.x > maxX) {
                    binding.graph
                        //.series[key]
                        //.appendData(point, true, 5000)
                    maxX = point.x
                }
            }
            */

            //binding.graph.series[key]
            //seriesContainer.add(key, dpoints)
            binding.executePendingBindings()
        }
    }

    private fun getSeries(key: Int) : LineGraphSeries<DataPoint> =
        when(key) {
            INBOUND_IDX -> inbound
            OUTBOUND_IDX -> outbound
            else -> throw IllegalArgumentException()
        }



}