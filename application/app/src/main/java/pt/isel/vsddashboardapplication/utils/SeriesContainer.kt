package pt.isel.vsddashboardapplication.utils

import com.jjoe64.graphview.series.DataPoint
import com.jjoe64.graphview.series.LineGraphSeries
import java.util.*
import kotlin.collections.ArrayList

class SeriesContainer {

    private val lineGraphSeriesMap = LinkedList<Container>()

    private data class Container (
        val title: String,
        val points: ArrayList<DataPoint> = ArrayList<DataPoint>(),
        val series: LineGraphSeries<DataPoint> = LineGraphSeries<DataPoint>()
    ) {
        fun alterRange(min: Double? = null, max: Double? = null){
            val newList = points.filter { point ->
                        min?.let { point.x >= min } ?: true &&
                        max?.let { point.x <= max } ?: true
            }
            points.clear()
            points.addAll(newList)
            series.resetData(newList.toTypedArray())
        }
    }

    /**
     * Adds a list of points to the series. If the series doesn't exist, one will be created.
     * @param title: the series identifier;
     * @param list: the list of xy graph points
     */
    fun add(title: String, list: List<DataPoint>) {
        var container = lineGraphSeriesMap.find { it.title == title }
        if(container == null){
            container = Container(title)
            lineGraphSeriesMap.add(container)
        }

        val newList = list
            .sortedBy { it.x }
            .takeIf { container.points.contains(it as DataPoint) }

        if(!newList.isNullOrEmpty()){
            container.points.addAll(newList)
            if(container.points.isEmpty()){
                container.series.resetData(newList.toTypedArray())
            } else if(newList.first().x > container.points.last().x) {
                container.points.sortBy { it.x }
                container.series.resetData(container.points.toTypedArray())
            } else {
                for (point in newList)
                    container.series.appendData(point, true, container.points.size)

            }
        }
    }

    fun get(key: String) = lineGraphSeriesMap.find { it.title == key }?.series



}