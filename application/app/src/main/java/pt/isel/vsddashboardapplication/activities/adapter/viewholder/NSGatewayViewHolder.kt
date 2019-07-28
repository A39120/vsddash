package pt.isel.vsddashboardapplication.activities.adapter.viewholder

import android.view.View
import pt.isel.vsddashboardapplication.model.NSGateway
import pt.isel.vsddashboardapplication.databinding.ItemGatewayBinding

class NSGatewayViewHolder(private val binding: ItemGatewayBinding) : BindingViewHolder<NSGateway>(binding) {

    override fun bind(item: NSGateway, onClick: ((NSGateway, View) -> Unit)?) {
        binding.gateway = item
        binding.gatewayInfo.setOnClickListener { onClick?.invoke(item, binding.gatewayInfo) }
        binding.executePendingBindings()
    }

}