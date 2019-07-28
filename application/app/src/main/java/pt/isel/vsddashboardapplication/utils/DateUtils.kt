package pt.isel.vsddashboardapplication.utils

import android.content.Context
import android.text.format.DateFormat
import java.text.SimpleDateFormat
import java.util.*

object DateUtils {

    fun parse(dateVal: Long, context: Context): String? {
        val date = Date(dateVal)
        val format = DateFormat.getDateFormat(context)
        return format.format(date)
    }

}