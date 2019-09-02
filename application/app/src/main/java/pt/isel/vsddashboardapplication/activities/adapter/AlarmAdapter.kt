package pt.isel.vsddashboardapplication.activities.adapter

import androidx.recyclerview.widget.DiffUtil
import pt.isel.vsddashboardapplication.R
import pt.isel.vsddashboardapplication.activities.adapter.viewholder.AlarmViewHolder
import pt.isel.vsddashboardapplication.model.Alarm
import pt.isel.vsddashboardapplication.databinding.ItemAlarmBinding

class AlarmAdapter : BaseAdapter<Alarm, AlarmViewHolder, ItemAlarmBinding>(diff = diff) {

    override fun getItemLayoutRes(): Int = R.layout.item_alarm

    override fun getViewHolder(binding: ItemAlarmBinding): AlarmViewHolder =
            AlarmViewHolder(binding)

    companion object {
        private val diff  = object : DiffUtil.ItemCallback<Alarm>() {
            override fun areItemsTheSame(oldItem: Alarm, newItem: Alarm): Boolean {
                return oldItem.iD == newItem.iD
            }

            override fun areContentsTheSame(oldItem: Alarm, newItem: Alarm): Boolean {
                return oldItem.lastUpdatedDate == newItem.lastUpdatedDate
            }

        }
    }

}
