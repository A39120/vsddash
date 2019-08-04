package pt.isel.vsddashboardapplication.activities.adapter

import android.view.View
import pt.isel.vsddashboardapplication.R
import pt.isel.vsddashboardapplication.activities.adapter.viewholder.VscViewHolder
import pt.isel.vsddashboardapplication.databinding.ItemVscBinding
import pt.isel.vsddashboardapplication.model.VSC

/**
 * The VSC, adapts the list of vsc
 * @param onClickListener: the action that will occur when an item is clicked on
 */
class VscAdapter( onClickListener: (VSC, View) -> Unit )
    : BaseAdapter<VSC, VscViewHolder, ItemVscBinding>(onClickListener) {

    override fun getItemLayoutRes(): Int = R.layout.item_vsc

    override fun getViewHolder(binding: ItemVscBinding) = VscViewHolder(binding)
}
