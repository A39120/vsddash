package pt.isel.vsddashboardapplication.activities.adapter

import android.view.View
import androidx.recyclerview.widget.DiffUtil
import pt.isel.vsddashboardapplication.R
import pt.isel.vsddashboardapplication.activities.adapter.viewholder.ApmViewHolder
import pt.isel.vsddashboardapplication.databinding.ItemApmBinding
import pt.isel.vsddashboardapplication.model.APM

class ApmAdapter(onClickListener: (APM, View) -> Unit)
    : BaseAdapter<APM, ApmViewHolder, ItemApmBinding>(onClickListener, diff){
    companion object {
        private val diff = object: DiffUtil.ItemCallback<APM>(){
            override fun areContentsTheSame(oldItem: APM, newItem: APM): Boolean =
                oldItem.lastUpdatedDate == newItem.lastUpdatedDate
            override fun areItemsTheSame(oldItem: APM, newItem: APM): Boolean =
                oldItem.iD == newItem.iD
        }
    }

    override fun getItemLayoutRes(): Int = R.layout.item_apm

    override fun getViewHolder(binding: ItemApmBinding): ApmViewHolder =
        ApmViewHolder(binding)
}
