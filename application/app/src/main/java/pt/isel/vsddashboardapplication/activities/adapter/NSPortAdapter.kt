package pt.isel.vsddashboardapplication.activities.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import pt.isel.vsddashboardapplication.R
import pt.isel.vsddashboardapplication.activities.adapter.viewholder.NSPortViewHolder
import pt.isel.vsddashboardapplication.repository.pojo.NSPort
import pt.isel.vsddashboardapplication.databinding.PortItemBinding

class NSPortAdapter (
    private val onClickListener: (NSPort, View) -> Unit
) : RecyclerView.Adapter<NSPortViewHolder>() {


    private val values: ArrayList<NSPort> = ArrayList()
    override fun getItemCount(): Int = values.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NSPortViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = DataBindingUtil.inflate<PortItemBinding>(inflater, R.layout.port_item, parent, false)
        return NSPortViewHolder(binding)
    }

    override fun onBindViewHolder(holder: NSPortViewHolder, position: Int) {
        val item = values[position]
        holder.bind(item, onClickListener)
    }

    fun setList(list: List<NSPort>?){
        list?.let {
            this.values.clear()
            this.values.addAll(list)
        }
        notifyDataSetChanged()
    }

}
