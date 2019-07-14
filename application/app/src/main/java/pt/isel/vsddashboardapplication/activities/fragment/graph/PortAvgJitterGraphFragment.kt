package pt.isel.vsddashboardapplication.activities.fragment.graph

import androidx.annotation.StringRes
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.jjoe64.graphview.Viewport
import com.jjoe64.graphview.series.DataPoint
import com.jjoe64.graphview.series.LineGraphSeries
import pt.isel.vsddashboardapplication.R
import pt.isel.vsddashboardapplication.viewmodel.ProbestatsViewModel

/**
 * Responsible for displaying the inbound and outbound jitter from a port
 * of a NSG
 */
class PortAvgJitterGraphFragment : BaseGraphFragment<ProbestatsViewModel>() {

    override fun setViewport(viewport: Viewport) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    private val series = HashMap<String, LineGraphSeries<DataPoint>>()

    override fun getMaxData(): Int = DEFAULT_MAX

    override fun assignViewModel(): ProbestatsViewModel =
        ViewModelProviders.of(this)[ProbestatsViewModel::class.java]

    override fun observeViewModel() {
        /*viewModel.liveData.observe(this, Observer {list ->
            list.groupBy { it.appGroupID }
                .entries
                .forEach { entry ->
                    val name = entry.key
                    val list = entry.value


                }
        })*/
    }

    override fun initViewModel() {
        //TODO: This
    }



    @StringRes
    override fun getHorizontalTitleResource(): Int = R.string.avg_jitter

    @StringRes
    override fun getVerticalTitleResource(): Int = R.string.time

}