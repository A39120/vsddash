package pt.isel.vsddashboardapplication.activities.adapter

import android.view.View
import androidx.annotation.LayoutRes
import pt.isel.vsddashboardapplication.R
import pt.isel.vsddashboardapplication.activities.adapter.viewholder.VPortViewHolder
import pt.isel.vsddashboardapplication.databinding.ItemVportBinding
import pt.isel.vsddashboardapplication.model.VPort

/**
 * The NSPortAdapter, adapts the list of ports
 * @param onClickListener: the action that will occur when an item is clicked on
 */
class VportAdapter ( onClickListener: (VPort, View) -> Unit )
    : BaseAdapter<VPort, VPortViewHolder, ItemVportBinding>(onClickListener) {

    @LayoutRes
    override fun getItemLayoutRes(): Int = R.layout.item_vport

    override fun getViewHolder(binding: ItemVportBinding): VPortViewHolder = VPortViewHolder(binding)

}
