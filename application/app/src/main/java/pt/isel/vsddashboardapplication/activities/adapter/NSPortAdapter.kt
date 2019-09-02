package pt.isel.vsddashboardapplication.activities.adapter

import android.view.View
import androidx.annotation.LayoutRes
import androidx.recyclerview.widget.DiffUtil
import pt.isel.vsddashboardapplication.R
import pt.isel.vsddashboardapplication.activities.adapter.viewholder.NSPortViewHolder
import pt.isel.vsddashboardapplication.databinding.ItemPortBinding
import pt.isel.vsddashboardapplication.model.NSPort

/**
 * The NSPortAdapter, adapts the list of ports
 * @param onClickListener: the action that will occur when an item is clicked on
 */
class NSPortAdapter ( onClickListener: (NSPort, View) -> Unit )
    : BaseAdapter<NSPort, NSPortViewHolder, ItemPortBinding>(onClickListener, diff) {
    companion object{
        private val diff = object  : DiffUtil.ItemCallback<NSPort>(){
            override fun areItemsTheSame(oldItem: NSPort, newItem: NSPort): Boolean =
                oldItem.iD == newItem.iD

            override fun areContentsTheSame(oldItem: NSPort, newItem: NSPort): Boolean =
                oldItem.lastUpdatedDate == newItem.lastUpdatedDate

        }
    }

    @LayoutRes
    override fun getItemLayoutRes(): Int = R.layout.item_port

    override fun getViewHolder(binding: ItemPortBinding): NSPortViewHolder = NSPortViewHolder(binding)

}
