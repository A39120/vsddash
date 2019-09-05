package pt.isel.vsddashboardapplication.activities.adapter

import androidx.recyclerview.widget.DiffUtil
import pt.isel.vsddashboardapplication.R
import pt.isel.vsddashboardapplication.activities.adapter.viewholder.HealthViewHolder
import pt.isel.vsddashboardapplication.databinding.ItemHealthBinding
import pt.isel.vsddashboardapplication.model.Health

class HealthAdapter
    : BaseAdapter<Health, HealthViewHolder, ItemHealthBinding>(null, diff){
    companion object{
        private val diff = object : DiffUtil.ItemCallback<Health>(){
            override fun areItemsTheSame(oldItem: Health, newItem: Health): Boolean =
                oldItem.name == newItem.name

            override fun areContentsTheSame(oldItem: Health, newItem: Health): Boolean =
                oldItem.iD == newItem.iD &&
                oldItem.status == newItem.status &&
                oldItem.summary == newItem.summary

        }
    }

    override fun getItemLayoutRes(): Int = R.layout.item_health

    override fun getViewHolder(binding: ItemHealthBinding): HealthViewHolder =
        HealthViewHolder(binding)
}
