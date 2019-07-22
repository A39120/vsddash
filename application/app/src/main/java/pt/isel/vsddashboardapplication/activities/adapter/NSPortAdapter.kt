package pt.isel.vsddashboardapplication.activities.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import pt.isel.vsddashboardapplication.R
import pt.isel.vsddashboardapplication.activities.adapter.viewholder.NSPortViewHolder
import pt.isel.vsddashboardapplication.model.NSPort
import pt.isel.vsddashboardapplication.databinding.PortItemBinding

/**
 * The NSPortAdapter, adapts the list of ports
 * @param onClickListener: the action that will occur when an item is clicked on
 */
class NSPortAdapter ( onClickListener: (NSPort, View) -> Unit )
    : BaseAdapter<NSPort, NSPortViewHolder, PortItemBinding>(onClickListener) {

    @LayoutRes
    override fun getItemLayoutRes(): Int = R.layout.port_item

    override fun getViewHolder(binding: PortItemBinding): NSPortViewHolder = NSPortViewHolder(binding)

}
