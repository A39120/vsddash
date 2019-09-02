package pt.isel.vsddashboardapplication.activities.adapter

import android.view.View
import androidx.recyclerview.widget.DiffUtil
import pt.isel.vsddashboardapplication.R
import pt.isel.vsddashboardapplication.activities.adapter.viewholder.EnterpriseViewHolder
import pt.isel.vsddashboardapplication.databinding.ItemEnterpriseBinding
import pt.isel.vsddashboardapplication.model.Enterprise

class EnterpriseAdapter(onClickListener: (Enterprise, View) -> Unit)
    : BaseAdapter<Enterprise, EnterpriseViewHolder, ItemEnterpriseBinding>(onClickListener, diff){
    companion object{
        private val diff = object : DiffUtil.ItemCallback<Enterprise>(){
            override fun areItemsTheSame(oldItem: Enterprise, newItem: Enterprise): Boolean =
                oldItem.iD == newItem.iD

            override fun areContentsTheSame(oldItem: Enterprise, newItem: Enterprise): Boolean =
                oldItem.lastUpdatedDate == newItem.lastUpdatedDate

        }
    }

    override fun getItemLayoutRes(): Int = R.layout.item_enterprise

    override fun getViewHolder(binding: ItemEnterpriseBinding): EnterpriseViewHolder =
        EnterpriseViewHolder(binding)
}