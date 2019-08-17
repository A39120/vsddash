package pt.isel.vsddashboardapplication.utils

import java.util.*

object TimeRangeCalculator {

    fun getLastHourRange() : DateRange =
        getRange(Calendar.HOUR, -1)

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

    fun getHourAfter(start: Long): DateRange {
        val calendar = Calendar.getInstance()
        calendar.timeInMillis = start
        calendar.add(1, Calendar.HOUR)
        return DateRange(start, calendar.timeInMillis)
    }

    fun getHourBefore(end: Long) : DateRange {
        val calendar = Calendar.getInstance()
        calendar.timeInMillis = end
        calendar.add(-1, Calendar.HOUR)
        return DateRange(calendar.timeInMillis, end)
    }


    private fun getRange(unit: Int, amount: Int) : DateRange {

        val calendar = Calendar.getInstance()
        val current = calendar.timeInMillis

        calendar.add(unit, amount)
        val yesterday = calendar.timeInMillis
        return DateRange(yesterday, current)
    }

    /**
     *
     */
    fun getDefaultCustomRange(from: Long? = null, to: Long? = null): DateRange{
        if(from == null && to == null){
            return getLastHourRange()
        } else if(from == null) {
            return getHourBefore(to!!)
        } else if(to == null) {
            return getHourAfter(from)
        }

        return TimeRangeCalculator.getCustomRange(from ,to)
    }



}