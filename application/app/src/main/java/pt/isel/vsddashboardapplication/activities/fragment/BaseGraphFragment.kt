package pt.isel.vsddashboardapplication.activities.fragment

import android.content.Context
import android.util.Log
import androidx.annotation.LayoutRes
import androidx.annotation.StringRes
import com.jjoe64.graphview.DefaultLabelFormatter
import com.jjoe64.graphview.GraphView
import com.jjoe64.graphview.LabelFormatter
import androidx.lifecycle.Observer
import com.jjoe64.graphview.Viewport
import com.jjoe64.graphview.series.DataPoint
import com.jjoe64.graphview.series.LineGraphSeries
import dagger.android.support.AndroidSupportInjection
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import pt.isel.vsddashboardapplication.R
import pt.isel.vsddashboardapplication.activities.fragment.base.BaseChildFragment
import pt.isel.vsddashboardapplication.activities.fragment.graph.BaseProbestatsGraphFragment
import pt.isel.vsddashboardapplication.databinding.FragmentGraphBinding
import java.text.SimpleDateFormat
import java.util.*

abstract class BaseGraphFragment: BaseChildFragment<FragmentGraphBinding>() {
    companion object { private const val TAG = "FRAG/BGRAPH" }

    private var wasScrolled = false

    private val HORIZONTAL_LINES = 2
    private val VERTICAL_LINES = 2
    private val HORIZONTAL_LABEL_ANGLE = 0


    /**
     * The graph layout
     */
    @LayoutRes
    override fun getLayoutRes(): Int = R.layout.fragment_graph

    override fun setBindingObjects() {
        CoroutineScope(Dispatchers.Main).launch {
            prepareGraph()
        }
    }

    /**
     * Adds a series to the graph
     * @param series: a list of sorted points in the graph
     */
    protected suspend fun addSeries(series: LineGraphSeries<DataPoint>) = withContext(Dispatchers.Main){
        Log.d(TAG, "Adding a new series: ${series.title?:"unnamed"} to the graph")
        binding.graph.addSeries(series)
    }

    /**
     * Dynamically prepares the GraphView
     */
    private suspend fun prepareGraph() = withContext(Dispatchers.Main) {
        Log.d(TAG, "Preparing graph")
        binding.graph.let { graph ->
            Log.d(TAG, "Setting Viewport")
            setViewport(graph.viewport)

            Log.d(TAG, "Setting grid label definitions")
            graph.gridLabelRenderer.let {
                //it.numVerticalLabels = VERTICAL_LINES
                it.numHorizontalLabels = HORIZONTAL_LINES

                it.setHorizontalLabelsAngle(HORIZONTAL_LABEL_ANGLE)

                it.horizontalAxisTitle = resources.getString(getHorizontalTitleResource())
                it.verticalAxisTitle = resources.getString(getVerticalTitleResource())

                it.padding = resources.getDimension(R.dimen.margin_default).toInt()

                it.labelFormatter = getHorizontalFormat()
                it.setHumanRounding(true)
            }

            graph.setOnScrollChangeListener { _, scrollX, _, oldScrollX, _ ->
                wasScrolled = scrollX < oldScrollX || scrollX < graph.viewport.getMaxX(true)
                Log.d(TAG, "wasScrolled is ${if(wasScrolled) "activated" else "deactivated"}")
            }
        }
        addSeries(binding.graph)
        binding.executePendingBindings()
    }

    abstract fun addSeries(graphView: GraphView)

    /**
     * Gets the horizontal label format, this can be overridden to display non-hourly X labels
     */
    protected fun getHorizontalFormat() : LabelFormatter {
        val simpleDateFormat = SimpleDateFormat("d/M hh:mm")
        val defaultLabelFormatter = object : DefaultLabelFormatter() {

            override fun formatLabel(value: Double, isValueX: Boolean): String {
                if (isValueX)
                    return simpleDateFormat.format(value)

                return super.formatLabel(value, isValueX)
            }
        }
        return defaultLabelFormatter

    }
        //DateAsXAxisLabelFormatter(this.context)//, DateFormat.getTimeInstance())

    /**
     * @return the resource id for the horizontal title
     */
    @StringRes
    protected abstract fun getHorizontalTitleResource() : Int

    /**
     * @return the resource id for the vertical title
     */
    @StringRes
    protected abstract fun getVerticalTitleResource() : Int

    /**
     * Appends data to a series
     */
    protected suspend fun appendData(series: LineGraphSeries<DataPoint>, dataPoint: DataPoint) = withContext(Dispatchers.Main){
        Log.d(TAG, "Appending data x: ${dataPoint.x}, y: ${dataPoint.y}")
        //series.appendData(dataPoint, !wasScrolled, getMaxData())
        series.appendData(dataPoint, false, 1000)
    }

    protected fun appendData(series: LineGraphSeries<DataPoint>, points: List<DataPoint>) {
        Log.d(TAG, "Appending list of data points - ${points.size}")
        if(!points.isNullOrEmpty()) {
            CoroutineScope(Dispatchers.Main).launch {
                binding.graph.viewport.apply {
                    isScalable = true
                    isScrollable = true
                    val minX =  points.minBy { it.x }!!.x
                    val maxX = points.maxBy { it.x }!!.x
                    val maxY = points.maxBy { it.y }!!.y
                    setBounds(minX, maxX, maxY)

                    isXAxisBoundsManual = true
                    isYAxisBoundsManual = true

                    setDrawBorder(true)
                    setScrollableY(true)
                }
                series.resetData(points.toTypedArray())
                binding.graph.removeAllSeries()
                binding.graph.addSeries(series)
                binding.executePendingBindings()
            }
        }
    }

    protected fun getMaxData() : Int = 100

    override fun onAttach(context: Context) {
        AndroidSupportInjection.inject(this)
        super.onAttach(context)
    }


    /**
     * Sets up viewport.
     * @param viewport: the viewport of the graph
     */
    fun setViewport(viewport: Viewport) {
        viewport.apply {
            isScalable = true
            isScrollable = true
            isXAxisBoundsManual = true
            isYAxisBoundsManual = true

            setMinY(0.0)
            setMaxY(1.0)

            setMinX(0.0)
            setMaxX(0.0)

            binding.executePendingBindings()
        }
    }

    private var minX = Double.MAX_VALUE
    private var maxX = Double.MIN_VALUE
    private var maxY = 1.0

    fun setBounds(minX: Double, maxX: Double, maxY: Double){
        if(minX < this.minX)
            this.minX = minX

        if(maxX > this.maxX)
            this.maxX = maxX

        if(maxY > this.maxY)
            this.maxY = maxY

        binding.graph.viewport.apply {
            isScalable = true
            isScrollable = true

            isYAxisBoundsManual = true
            isXAxisBoundsManual = true

            setMinX(minX)
            setMaxX(maxX)
            setMaxY(maxY)
        }
        binding.executePendingBindings()
    }


    protected fun <T> observe(series: LineGraphSeries<DataPoint>, mapper: ((T) -> DataPoint?)) =
        Observer<List<T>> { list : List<T> ->
            Log.d(TAG, "Probestats suffered alterations - ${list.size}")
            val mapped = (list.mapNotNull(mapper)
                .sortedBy { it.x } ?: listOf<DataPoint>())

            Log.d(TAG, "Resetting data - ${mapped.size}")
            CoroutineScope(Dispatchers.Main).launch {
                appendData(series, mapped)
            }
        }

}