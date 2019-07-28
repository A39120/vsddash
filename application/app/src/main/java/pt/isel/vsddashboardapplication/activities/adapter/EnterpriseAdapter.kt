package pt.isel.vsddashboardapplication.activities.adapter

import android.view.View
import pt.isel.vsddashboardapplication.R
import pt.isel.vsddashboardapplication.activities.adapter.viewholder.EnterpriseViewHolder
import pt.isel.vsddashboardapplication.databinding.ItemEnterpriseBinding
import pt.isel.vsddashboardapplication.model.Enterprise

class EnterpriseAdapter(onClickListener: (Enterprise, View) -> Unit)
    : BaseAdapter<Enterprise, EnterpriseViewHolder, ItemEnterpriseBinding>(onClickListener){

    override fun getItemLayoutRes(): Int = R.layout.item_enterprise

    override fun getViewHolder(binding: ItemEnterpriseBinding): EnterpriseViewHolder =
        EnterpriseViewHolder(binding)
}