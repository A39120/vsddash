package pt.isel.vsddashboardapplication.activities.adapter

import pt.isel.vsddashboardapplication.R
import pt.isel.vsddashboardapplication.activities.adapter.viewholder.EnterpriseViewHolder
import pt.isel.vsddashboardapplication.databinding.EnterpriseItemBinding
import pt.isel.vsddashboardapplication.model.Enterprise

class EnterpriseAdapter : BaseAdapter<Enterprise, EnterpriseViewHolder, EnterpriseItemBinding>() {

    override fun getItemLayoutRes(): Int = R.layout.enterprise_item

    override fun getViewHolder(binding: EnterpriseItemBinding): EnterpriseViewHolder =
        EnterpriseViewHolder(binding)
}