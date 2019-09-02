package pt.isel.vsddashboardapplication.utils

import android.content.Context
import android.text.format.DateFormat
import java.text.SimpleDateFormat
import java.util.*

class DateUtils {
    companion object {

        fun parse(dateVal: Long, context: Context): String? {
            val date = Date(dateVal)
            val format = DateFormat.getDateFormat(context)
            return format.format(date)
        }

        fun getFormat() = SimpleDateFormat("yyyy-MM-dd hh:mm", Locale.UK)

        @JvmStatic
        fun toDate(value: Long): String {
            val format = getFormat()
            val date = Date(value)
            return format.format(date)
        }

    }
}


