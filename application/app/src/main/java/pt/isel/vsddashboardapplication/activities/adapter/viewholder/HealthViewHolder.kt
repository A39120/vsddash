package pt.isel.vsddashboardapplication.activities.adapter.viewholder

import android.view.View
import pt.isel.vsddashboardapplication.databinding.ItemHealthBinding
import pt.isel.vsddashboardapplication.model.Health

class HealthViewHolder(private val binding: ItemHealthBinding) : BindingViewHolder<Health>(binding) {

    override fun bind(item: Health, onClick: ((Health, View) -> Unit)?) {
        binding.health = item
        binding.executePendingBindings()
    }

}
