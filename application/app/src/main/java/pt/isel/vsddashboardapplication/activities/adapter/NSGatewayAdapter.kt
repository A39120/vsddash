package pt.isel.vsddashboardapplication.activities.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.ListAdapter
import pt.isel.vsddashboardapplication.activities.adapter.diffutil.NSGatewayDiffUtil
import pt.isel.vsddashboardapplication.activities.adapter.viewholder.NSGatewayViewHolder
import pt.isel.vsddashboardapplication.repository.pojo.NSGateway
import pt.isel.vsddashboardapplication.databinding.GatewayItemBinding

/**
 * Adapter to represent a list of NSGs
 */
class NSGatewayAdapter
    : ListAdapter<NSGateway, NSGatewayViewHolder>(NSGatewayDiffUtil()){

    private var list: List<NSGateway>? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NSGatewayViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = DataBindingUtil.inflate<GatewayItemBinding>(inflater, viewType, parent, false)
        return NSGatewayViewHolder(binding)
    }

    override fun onBindViewHolder(holder: NSGatewayViewHolder, position: Int) {
        list?.let { holder.bind(it[position]) }
    }

    fun setList(list: List<NSGateway>){ this.list = list }

}
