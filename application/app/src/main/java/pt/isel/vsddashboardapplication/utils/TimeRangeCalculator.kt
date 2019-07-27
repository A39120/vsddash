package pt.isel.vsddashboardapplication.utils

import java.util.*

object TimeRangeCalculator {

    fun getLastDayRange(): DateRange =
        getRange(Calendar.DAY_OF_YEAR, -1)

    fun getLastWeekRange() : DateRange =
        getRange(Calendar.WEEK_OF_YEAR, -1)

    fun getLastMonth() : DateRange =
        getRange(Calendar.MONTH, -1)

    fun getCustomRange(start: Long, end: Long) =
        DateRange(start, end)

    fun getCustomRange(start: Calendar, end: Calendar) =
         DateRange( start.timeInMillis, end.timeInMillis )


    private fun getRange(unit: Int, amount: Int) : DateRange {

        val calendar = Calendar.getInstance()
        val current = calendar.timeInMillis

        calendar.add(unit, amount)
        val yesterday = calendar.timeInMillis
        return DateRange(yesterday, current)
    }

}