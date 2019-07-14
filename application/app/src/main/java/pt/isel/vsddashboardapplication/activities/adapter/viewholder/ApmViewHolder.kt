package pt.isel.vsddashboardapplication.activities.adapter.viewholder

import android.view.View
import pt.isel.vsddashboardapplication.databinding.ApmItemBinding
import pt.isel.vsddashboardapplication.model.APM

class ApmViewHolder(private val binding: ApmItemBinding)
    : BindingViewHolder<APM>(binding) {

    override fun bind(item: APM, onClick: ((APM, View) -> Unit)?) {
        binding.apm = item
        binding.executePendingBindings()
    }

}
