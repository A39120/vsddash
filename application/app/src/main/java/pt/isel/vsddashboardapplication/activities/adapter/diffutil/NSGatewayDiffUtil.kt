package pt.isel.vsddashboardapplication.activities.adapter.diffutil

import androidx.recyclerview.widget.DiffUtil
import pt.isel.vsddashboardapplication.repository.pojo.NSGateway

class NSGatewayDiffUtil : DiffUtil.ItemCallback<NSGateway>() {

    override fun areItemsTheSame(oldItem: NSGateway, newItem: NSGateway): Boolean =
        oldItem == newItem

    override fun areContentsTheSame(oldItem: NSGateway, newItem: NSGateway): Boolean =
        oldItem == newItem


}
