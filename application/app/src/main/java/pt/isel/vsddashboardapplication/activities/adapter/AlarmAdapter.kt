package pt.isel.vsddashboardapplication.activities.adapter

import pt.isel.vsddashboardapplication.R
import pt.isel.vsddashboardapplication.activities.adapter.viewholder.AlarmViewHolder
import pt.isel.vsddashboardapplication.repository.pojo.Alarm
import pt.isel.vsddashboardapplication.databinding.AlarmItemBinding

class AlarmAdapter : BaseAdapter<Alarm, AlarmViewHolder, AlarmItemBinding>() {

    override fun getItemLayoutRes(): Int = R.layout.alarm_item

    override fun getViewHolder(binding: AlarmItemBinding): AlarmViewHolder =
            AlarmViewHolder(binding)

}
