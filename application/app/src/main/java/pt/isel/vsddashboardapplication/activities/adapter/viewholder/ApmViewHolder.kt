package pt.isel.vsddashboardapplication.activities.adapter.viewholder

import android.view.View
import pt.isel.vsddashboardapplication.databinding.ItemApmBinding
import pt.isel.vsddashboardapplication.model.APM

class ApmViewHolder(private val binding: ItemApmBinding)
    : BindingViewHolder<APM>(binding) {

    override fun bind(item: APM, onClick: ((APM, View) -> Unit)?) {
        binding.apm = item
        binding.executePendingBindings()
    }

}
