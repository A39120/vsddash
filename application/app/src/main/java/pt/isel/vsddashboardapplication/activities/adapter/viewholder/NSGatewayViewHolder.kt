package pt.isel.vsddashboardapplication.activities.adapter.viewholder

import android.view.View
import android.widget.ListView
import androidx.recyclerview.widget.RecyclerView
import pt.isel.vsddashboardapplication.repository.pojo.NSGateway
import pt.isel.vsddashboardapplication.databinding.GatewayItemBinding

class NSGatewayViewHolder(private val binding: GatewayItemBinding) : BindingViewHolder<NSGateway>(binding) {

    override fun bind(item: NSGateway) {
        binding.gateway = item
    }


}