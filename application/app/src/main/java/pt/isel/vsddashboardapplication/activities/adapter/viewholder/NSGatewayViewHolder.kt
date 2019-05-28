package pt.isel.vsddashboardapplication.activities.adapter.viewholder

import pt.isel.vsddashboardapplication.repository.pojo.NSGateway
import pt.isel.vsddashboardapplication.databinding.GatewayItemBinding

class NSGatewayViewHolder(private val binding: GatewayItemBinding) : BindingViewHolder<NSGateway>(binding) {

    override fun bind(item: NSGateway, onClick: (NSGateway) -> Unit) {
        binding.gateway = item
        binding.gatewayInfo.setOnClickListener { onClick(item) }
        binding.executePendingBindings()
    }

}