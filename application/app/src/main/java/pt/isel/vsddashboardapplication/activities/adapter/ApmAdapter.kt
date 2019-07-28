package pt.isel.vsddashboardapplication.activities.adapter

import android.view.View
import pt.isel.vsddashboardapplication.R
import pt.isel.vsddashboardapplication.activities.adapter.viewholder.ApmViewHolder
import pt.isel.vsddashboardapplication.databinding.ItemApmBinding
import pt.isel.vsddashboardapplication.model.APM

class ApmAdapter(onClickListener: (APM, View) -> Unit)
    : BaseAdapter<APM, ApmViewHolder, ItemApmBinding>(onClickListener){

    override fun getItemLayoutRes(): Int = R.layout.item_apm

    override fun getViewHolder(binding: ItemApmBinding): ApmViewHolder =
        ApmViewHolder(binding)
}
