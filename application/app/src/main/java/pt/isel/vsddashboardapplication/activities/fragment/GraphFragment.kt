package pt.isel.vsddashboardapplication.activities.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.jjoe64.graphview.series.DataPoint
import com.jjoe64.graphview.series.LineGraphSeries
import kotlinx.coroutines.*
import pt.isel.vsddashboardapplication.R
import pt.isel.vsddashboardapplication.communication.services.ElasticSearchServices
import pt.isel.vsddashboardapplication.communication.services.es.ElasticSearchService
import pt.isel.vsddashboardapplication.databinding.GraphFragmentBinding
import pt.isel.vsddashboardapplication.utils.SharedPreferenceKeys
import pt.isel.vsddashboardapplication.utils.sharedPreferences
import java.util.*

class GraphFragment : Fragment(){


    private lateinit var binding : GraphFragmentBinding
    private var service : ElasticSearchService? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        binding = DataBindingUtil.inflate(inflater, R.layout.graph_fragment, container, false)
        initService()

        CoroutineScope(Dispatchers.Main).launch { getData() }

        return binding.root
    }

    private fun initService(){
        val address = this.activity?.sharedPreferences()?.getString(SharedPreferenceKeys.CURRENTADDRESS, "")
        val port = 6200
        val uri = "https://$address:$port/"
        service = ElasticSearchServices.getInstance(uri)?.getService(ElasticSearchService::class.java)
    }

    private suspend fun prepareGraph(series : LineGraphSeries<DataPoint>) = withContext(Dispatchers.Main){
        binding.graph.addSeries(series)
    }

    private suspend fun getData() = withContext(Dispatchers.IO) {
        val search = service?.getDpiProbestats()?.await()
        val jitter : List<DataPoint> = search
            ?.hits
            ?.hits
            ?.filter { hit -> hit?.source?.avgDelay != null && hit?.source?.timestamp != null }
            ?.map { it.source!! }
            ?.map { dpi ->
                val timestamp = dpi.timestamp ?: 0
                val date = Date(timestamp)
                DataPoint(date, dpi.avgDelay?:0.0)
            }
            ?.sortedBy { it.x }
            ?: listOf()

        val graphSeries = LineGraphSeries(jitter.toTypedArray())

        prepareGraph(graphSeries)
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