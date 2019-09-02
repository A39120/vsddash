package pt.isel.vsddashboardapplication.activities.adapter

import android.view.View
import androidx.recyclerview.widget.DiffUtil
import pt.isel.vsddashboardapplication.R
import pt.isel.vsddashboardapplication.activities.adapter.viewholder.VscViewHolder
import pt.isel.vsddashboardapplication.databinding.ItemVscBinding
import pt.isel.vsddashboardapplication.model.VPort
import pt.isel.vsddashboardapplication.model.VSC

/**
 * The VSC, adapts the list of vsc
 * @param onClickListener: the action that will occur when an item is clicked on
 */
class VscAdapter( onClickListener: (VSC, View) -> Unit )
    : BaseAdapter<VSC, VscViewHolder, ItemVscBinding>(onClickListener, diff) {
    companion object {
        private val diff = object : DiffUtil.ItemCallback<VSC>(){
            override fun areItemsTheSame(oldItem: VSC, newItem: VSC): Boolean =
                oldItem.iD == newItem.iD


            override fun areContentsTheSame(oldItem: VSC, newItem: VSC): Boolean =
                oldItem.lastUpdatedDate == newItem.lastUpdatedDate
        }
    }

    override fun getItemLayoutRes(): Int = R.layout.item_vsc

    override fun getViewHolder(binding: ItemVscBinding) = VscViewHolder(binding)
}
