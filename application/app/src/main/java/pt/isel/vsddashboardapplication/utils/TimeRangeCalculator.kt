package pt.isel.vsddashboardapplication.utils

import java.util.*

object TimeRangeCalculator {

    fun getLast5Minutes() : DateRange =
        getRangeFromCurrent(Calendar.MINUTE, -5)

    fun getLastHourRange() : DateRange =
        getRangeFromCurrent(Calendar.HOUR, -1)

    fun getLastDayRange(): DateRange =
        getRangeFromCurrent(Calendar.DAY_OF_YEAR, -1)

    fun getLastWeekRange() : DateRange =
        getRangeFromCurrent(Calendar.WEEK_OF_YEAR, -1)

    fun getLastMonth() : DateRange =
        getRangeFromCurrent(Calendar.MONTH, -1)

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


    private fun getRangeFromCurrent(unit: Int, amount: Int) : DateRange {

        val calendar = Calendar.getInstance()
        val current = calendar.timeInMillis

        calendar.add(unit, amount)
        val yesterday = calendar.timeInMillis
        return DateRange(yesterday, null)
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