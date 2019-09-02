package pt.isel.vsddashboardapplication.utils

import android.widget.TextView
import androidx.databinding.BindingAdapter
import java.text.SimpleDateFormat
import java.util.*

object BindingAdapters {

    @BindingAdapter("timeInMillis")
    fun setDateText(view: TextView, timestamp: Long?){
        val calendar = Calendar.getInstance()
        if (timestamp != null)
            calendar.timeInMillis = timestamp

        val format = SimpleDateFormat()
        format.calendar = calendar

        view.setText( format.toString() )
    }

}