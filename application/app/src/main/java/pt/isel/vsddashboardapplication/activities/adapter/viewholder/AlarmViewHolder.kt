package pt.isel.vsddashboardapplication.activities.adapter.viewholder

import android.view.View
import pt.isel.vsddashboardapplication.databinding.AlarmItemBinding
import pt.isel.vsddashboardapplication.model.Alarm

class AlarmViewHolder(private val binding: AlarmItemBinding)
    : BindingViewHolder<Alarm>(binding) {

    override fun bind(item: Alarm, onClick: ((Alarm, View) -> Unit)?) {
        binding.alarm = item
        binding.executePendingBindings()
    }

}
