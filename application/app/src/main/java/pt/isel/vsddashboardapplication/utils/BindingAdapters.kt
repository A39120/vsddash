package pt.isel.vsddashboardapplication.utils

import android.widget.TextView
import androidx.databinding.BindingAdapter

object BindingAdapters {

    @BindingAdapter("time")
    fun setDateText(view: TextView, timestamp: Long){
        val text = DateUtils.parse(timestamp, view.context)
        view.text = text
    }

}