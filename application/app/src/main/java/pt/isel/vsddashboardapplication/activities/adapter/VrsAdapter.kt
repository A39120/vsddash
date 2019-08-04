package pt.isel.vsddashboardapplication.activities.adapter

import android.view.View
import pt.isel.vsddashboardapplication.R
import pt.isel.vsddashboardapplication.activities.adapter.viewholder.VrsViewHolder
import pt.isel.vsddashboardapplication.databinding.ItemVrsBinding
import pt.isel.vsddashboardapplication.model.VRS

class VrsAdapter(onClickListener: (VRS, View) -> Unit)
    : BaseAdapter<VRS, VrsViewHolder, ItemVrsBinding>(onClickListener){

    override fun getItemLayoutRes(): Int = R.layout.item_enterprise

    override fun getViewHolder(binding: ItemVrsBinding): VrsViewHolder =
        VrsViewHolder(binding)
}
