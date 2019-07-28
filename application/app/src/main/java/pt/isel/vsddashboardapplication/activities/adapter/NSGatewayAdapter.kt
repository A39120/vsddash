package pt.isel.vsddashboardapplication.activities.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import pt.isel.vsddashboardapplication.R
import pt.isel.vsddashboardapplication.activities.adapter.viewholder.NSGatewayViewHolder
import pt.isel.vsddashboardapplication.databinding.ItemGatewayBinding
import pt.isel.vsddashboardapplication.model.NSGateway

/**
 * Adapter to represent a list of NSGs
 */
class NSGatewayAdapter (
    private val onClickListener: (NSGateway, View) -> Unit
) : RecyclerView.Adapter<NSGatewayViewHolder>() {

    private val values: ArrayList<NSGateway> = ArrayList()
    override fun getItemCount(): Int = values.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NSGatewayViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = DataBindingUtil.inflate<ItemGatewayBinding>(inflater, R.layout.item_gateway, parent, false)
        return NSGatewayViewHolder(binding)
    }

    override fun onBindViewHolder(holder: NSGatewayViewHolder, position: Int) {
        val item = values[position]
        holder.bind(item, onClickListener)
    }

    fun setList(list: List<NSGateway>?){
        list?.let {
            this.values.clear()
            this.values.addAll(list)
        }

        notifyDataSetChanged()
    }

}
