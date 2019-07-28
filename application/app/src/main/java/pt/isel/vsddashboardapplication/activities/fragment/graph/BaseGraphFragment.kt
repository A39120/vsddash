package pt.isel.vsddashboardapplication.activities.fragment.graph

import android.content.Context
import android.util.Log
import androidx.annotation.StringRes
import androidx.lifecycle.ViewModel
import com.jjoe64.graphview.GraphView
import com.jjoe64.graphview.Viewport
import com.jjoe64.graphview.helper.DateAsXAxisLabelFormatter
import com.jjoe64.graphview.series.DataPoint
import com.jjoe64.graphview.series.LineGraphSeries
import dagger.android.support.AndroidSupportInjection
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import pt.isel.vsddashboardapplication.R
import pt.isel.vsddashboardapplication.activities.fragment.base.BaseFragment
import pt.isel.vsddashboardapplication.databinding.FragmentGraphBinding
import java.text.DateFormat

abstract class BaseGraphFragment<T : ViewModel> : BaseFragment<T, FragmentGraphBinding>() {
    companion object {
        private const val TAG = "FRAG/BGRAPH"
        const val DEFAULT_MAX = 30
    }

    private var wasScrolled = false

    private val HORIZONTAL_LINES = 5
    private val VERTICAL_LINES = 2
    private val HORIZONTAL_LABEL_ANGLE = 45


    override fun getLayoutRes(): Int = R.layout.fragment_graph

    override fun setBindingObjects() {
        CoroutineScope(Dispatchers.Main).launch { prepareGraph() }
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
                it.numVerticalLabels = VERTICAL_LINES
                it.numHorizontalLabels = HORIZONTAL_LINES

                it.setHorizontalLabelsAngle(HORIZONTAL_LABEL_ANGLE)

                it.horizontalAxisTitle = resources.getString(getHorizontalTitleResource())
                it.verticalAxisTitle = resources.getString(getVerticalTitleResource())

                it.padding = resources.getDimension(R.dimen.margin_default).toInt()

                it.labelFormatter = getHorizontalFormat()
                it.setHumanRounding(false)
            }

            graph.setOnScrollChangeListener { _, scrollX, _, oldScrollX, _ ->
                wasScrolled = scrollX < oldScrollX || scrollX < graph.viewport.getMaxX(true)
                Log.d(TAG, "wasScrolled is ${if(wasScrolled) "activated" else "deactivated"}")
            }

            addSeries(binding.graph)
        }
    }

    abstract fun addSeries(graphView: GraphView)

    /**
     * Gets the horizontal label format, this can be overridden to display non-hourly X labels
     */
    protected fun getHorizontalFormat() =
        DateAsXAxisLabelFormatter(this.context, DateFormat.getTimeInstance())

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
     * Sets the view port preferences for the graph
     */
    protected abstract fun setViewport(viewport: Viewport)

    /**
     * Appends data to a series
     */
    protected suspend fun appendData(series: LineGraphSeries<DataPoint>, dataPoint: DataPoint) = withContext(Dispatchers.Main){
        Log.d(TAG, "Appending data x: ${dataPoint.x}, y: ${dataPoint.y}")
        //series.appendData(dataPoint, !wasScrolled, getMaxData())
        series.appendData(dataPoint, true, getMaxData())
    }

    protected abstract fun getMaxData() : Int

    override fun onAttach(context: Context) {
        AndroidSupportInjection.inject(this)
        super.onAttach(context)
    }
}