package pt.isel.vsddashboardapplication.activities.fragment.graph

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.StringRes
import androidx.databinding.DataBindingUtil
import com.jjoe64.graphview.Viewport
import com.jjoe64.graphview.helper.DateAsXAxisLabelFormatter
import com.jjoe64.graphview.series.DataPoint
import com.jjoe64.graphview.series.LineGraphSeries
import dagger.android.support.AndroidSupportInjection
import dagger.android.support.DaggerFragment
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import pt.isel.vsddashboardapplication.R
import pt.isel.vsddashboardapplication.databinding.GraphFragmentBinding
import java.text.DateFormat

abstract class BaseGraphFragment : DaggerFragment() {
    companion object {
        private const val TAG = "FRAG/BGRAPH"
    }

    private lateinit var binding: GraphFragmentBinding
    private var wasScrolled = false

    private val HORIZONTAL_LINES = 5
    private val VERTICAL_LINES = 2
    private val HORIZONTAL_LABEL_ANGLE = 45

    /**
     * Prepares the view for the graph_fragment shared by all the fragments that use a graph
     */
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        Log.d(TAG, "Creating graph view.")
        binding = DataBindingUtil.inflate(inflater, R.layout.graph_fragment, container, false)

        CoroutineScope(Dispatchers.Main).launch {
            prepareGraph()
            observeViewModel()
        }

        return binding.root
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
     * Observes the view model that will apply changes to the graph
     */
    protected abstract fun observeViewModel()

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
        }
    }

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
        series.appendData(dataPoint, !wasScrolled, getMaxData())
    }

    protected abstract fun getMaxData() : Int

    override fun onAttach(context: Context) {
        AndroidSupportInjection.inject(this)
        super.onAttach(context)
    }
}