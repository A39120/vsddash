package pt.isel.vsddashboardapplication.activities.adapter

import android.view.View
import androidx.annotation.LayoutRes
import androidx.recyclerview.widget.DiffUtil
import pt.isel.vsddashboardapplication.R
import pt.isel.vsddashboardapplication.activities.adapter.viewholder.VPortViewHolder
import pt.isel.vsddashboardapplication.databinding.ItemVportBinding
import pt.isel.vsddashboardapplication.model.VPort

/**
 * The NSPortAdapter, adapts the list of ports
 * @param onClickListener: the action that will occur when an item is clicked on
 */
class VportAdapter ( onClickListener: (VPort, View) -> Unit )
    : BaseAdapter<VPort, VPortViewHolder, ItemVportBinding>(onClickListener, diff) {
    companion object {
        private val diff = object : DiffUtil.ItemCallback<VPort>(){
            override fun areItemsTheSame(oldItem: VPort, newItem: VPort): Boolean =
                oldItem.iD == newItem.iD


            override fun areContentsTheSame(oldItem: VPort, newItem: VPort): Boolean =
                oldItem.lastUpdatedDate == newItem.lastUpdatedDate
        }
    }

    @LayoutRes
    override fun getItemLayoutRes(): Int = R.layout.item_vport

    override fun getViewHolder(binding: ItemVportBinding): VPortViewHolder = VPortViewHolder(binding)

}
