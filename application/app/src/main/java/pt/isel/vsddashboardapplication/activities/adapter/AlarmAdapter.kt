package pt.isel.vsddashboardapplication.activities.adapter

import pt.isel.vsddashboardapplication.R
import pt.isel.vsddashboardapplication.activities.adapter.viewholder.AlarmViewHolder
import pt.isel.vsddashboardapplication.model.Alarm
import pt.isel.vsddashboardapplication.databinding.ItemAlarmBinding

class AlarmAdapter : BaseAdapter<Alarm, AlarmViewHolder, ItemAlarmBinding>() {

    override fun getItemLayoutRes(): Int = R.layout.item_alarm

    override fun getViewHolder(binding: ItemAlarmBinding): AlarmViewHolder =
            AlarmViewHolder(binding)

}
