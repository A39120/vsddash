package pt.isel.vsddashboardapplication.activities.adapter

import android.view.View
import pt.isel.vsddashboardapplication.R
import pt.isel.vsddashboardapplication.activities.adapter.viewholder.ApmViewHolder
import pt.isel.vsddashboardapplication.databinding.ApmItemBinding
import pt.isel.vsddashboardapplication.model.APM

class ApmAdapter(onClickListener: (APM, View) -> Unit)
    : BaseAdapter<APM, ApmViewHolder, ApmItemBinding>(onClickListener){

    override fun getItemLayoutRes(): Int = R.layout.apm_item

    override fun getViewHolder(binding: ApmItemBinding): ApmViewHolder =
        ApmViewHolder(binding)
}
