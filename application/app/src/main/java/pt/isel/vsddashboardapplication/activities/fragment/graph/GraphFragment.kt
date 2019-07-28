package pt.isel.vsddashboardapplication.activities.fragment.graph

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.jjoe64.graphview.LegendRenderer
import com.jjoe64.graphview.helper.DateAsXAxisLabelFormatter
import com.jjoe64.graphview.series.DataPoint
import com.jjoe64.graphview.series.LineGraphSeries
import kotlinx.coroutines.*
import pt.isel.vsddashboardapplication.R
import pt.isel.vsddashboardapplication.databinding.FragmentGraphBinding
import pt.isel.vsddashboardapplication.repository.services.es.DpiProbestatsServices
import pt.isel.vsddashboardapplication.repository.services.ElasticSearchRetrofitSingleton
import pt.isel.vsddashboardapplication.utils.SharedPreferenceKeys
import pt.isel.vsddashboardapplication.utils.sharedPreferences
import java.text.SimpleDateFormat
import java.util.*

class GraphFragment : Fragment(){


    private lateinit var binding : FragmentGraphBinding
    private var service : DpiProbestatsServices? = null
    private var wasScrolled : Boolean = false


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_graph, container, false)
        initService()

        prepareGraph()
        CoroutineScope(Dispatchers.IO).launch { getData() }

        return binding.root
    }

    private fun initService(){
        val address = this.activity?.sharedPreferences()?.getString(SharedPreferenceKeys.CURRENTADDRESS, "")
        val port = 6200
        val uri = "https://$address:$port/"
        ElasticSearchRetrofitSingleton.set(uri)
        service = ElasticSearchRetrofitSingleton.dpiProbestats()
    }

    private  fun prepareGraph() {
        binding.graph.let { graph ->
            val formatter = SimpleDateFormat("hh:mm:ss")
            graph.viewport.let { vp ->
                vp.isScalable = true
                vp.isScrollable = true
                vp.setMinY(0.0)
                vp.setMaxY(3.0)
                val minX = 1000.0
                val maxX = 30*minX.toDouble()
                vp.setMinX(minX)
                vp.setMaxX(maxX)
                vp.isXAxisBoundsManual = true
                vp.isYAxisBoundsManual = false
            }

            graph.gridLabelRenderer.let {
                it.numVerticalLabels = 2
                it.numHorizontalLabels = 5
                it.setHorizontalLabelsAngle(45)
                it.horizontalAxisTitle = resources.getString(R.string.avg_jitter)
                it.verticalAxisTitle = resources.getString(R.string.time)
                it.padding = resources.getDimension(R.dimen.margin_default).toInt()
                it.labelFormatter = DateAsXAxisLabelFormatter(this.context, formatter)
                it.setHumanRounding(false)
            }

            graph.legendRenderer = LegendRenderer(graph)
            graph.setOnScrollChangeListener { _, scrollX, _, oldScrollX, _ ->
                wasScrolled = scrollX < oldScrollX || scrollX < graph.viewport.getMaxX(true)
            }
        }
        //binding.graph.addSeries(series)
    }

    private suspend fun getData() = withContext(Dispatchers.IO) {
        val search = service?.getDpiProbestats(
            query = arrayOf("SourceNSG:MV-NSG", "APMGroup:Diamond"),
            sort = "timestamp:desc",
            size = 30
        )?.await()

        val graphSeries = LineGraphSeries<DataPoint>()
        graphSeries.setAnimated(true)
        graphSeries.title = "Diamond"
        graphSeries.isDrawBackground = false
        addSeries(graphSeries)


        val list = search?.hits?.hits?.sortedBy { hit -> hit.source?.timestamp } ?: listOf()

        for(hit in list){
            hit.source?.run {
                if ((timestamp > 0) && (avgJitter != null)) {
                    val date = Date(timestamp)
                    val dataPoint = DataPoint(date, avgJitter)
                    runBlocking(Dispatchers.Main) {
                        graphSeries.appendData(dataPoint, !wasScrolled, 20)
                    }
                }
            }
        }
    }

    private suspend fun addSeries( series: LineGraphSeries<DataPoint> ) {
        runBlocking(Dispatchers.Main) {
            binding.graph.addSeries(series)
            binding.graph.legendRenderer.isVisible = true
            binding.graph.legendRenderer.resetStyles()
        }
    }




    /*
    private fun prepareGraph() {
        binding.plot.rangeStepMode = StepMode.INCREMENT_BY_VAL
        binding.plot.domainStepMode = StepMode.INCREMENT_BY_VAL
        binding.plot.setRangeBoundaries(0, 100, BoundaryMode.FIXED)
        binding.plot.setDomainBoundaries(0, BoundaryMode.AUTO, 300, BoundaryMode.GROW)
        binding.plot.setDomainLabel("Time")
        binding.plot.setRangeLabel("Percentage de uso %")
        val series = listOf(10.5, 20.0, 30.0, 40.0, 50.0)
        val seriesX = SimpleXYSeries(series, SimpleXYSeries.ArrayFormat.Y_VALS_ONLY, "Series")
        binding.plot.addSeries(seriesX, LineAndPointFormatter())
    }
    */


}