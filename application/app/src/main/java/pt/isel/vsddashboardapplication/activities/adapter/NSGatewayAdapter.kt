package pt.isel.vsddashboardapplication.activities.adapter

import android.view.View
import androidx.recyclerview.widget.DiffUtil
import pt.isel.vsddashboardapplication.R
import pt.isel.vsddashboardapplication.activities.adapter.viewholder.NSGatewayViewHolder
import pt.isel.vsddashboardapplication.databinding.ItemGatewayBinding
import pt.isel.vsddashboardapplication.model.NSGateway

/**
 * Adapter to represent a list of NSGs
 */
class NSGatewayAdapter (
    onClickListener: (NSGateway, View) -> Unit
) : BaseAdapter<NSGateway, NSGatewayViewHolder, ItemGatewayBinding>(onClickListener, diff){
    companion object {
        private val diff = object: DiffUtil.ItemCallback<NSGateway>(){
            override fun areContentsTheSame(oldItem: NSGateway, newItem: NSGateway): Boolean =
                oldItem == newItem

            override fun areItemsTheSame(oldItem: NSGateway, newItem: NSGateway): Boolean =
                oldItem.ID == newItem.ID
        }
    }

    override fun getItemLayoutRes(): Int = R.layout.item_gateway

    override fun getViewHolder(binding: ItemGatewayBinding): NSGatewayViewHolder =
        NSGatewayViewHolder(binding)
}
