package pt.isel.vsddashboardapplication.activities.adapter

import android.view.View
import androidx.recyclerview.widget.DiffUtil
import pt.isel.vsddashboardapplication.R
import pt.isel.vsddashboardapplication.activities.adapter.viewholder.VrsViewHolder
import pt.isel.vsddashboardapplication.databinding.ItemVrsBinding
import pt.isel.vsddashboardapplication.model.VRS

class VrsAdapter(onClickListener: (VRS, View) -> Unit)
    : BaseAdapter<VRS, VrsViewHolder, ItemVrsBinding>(onClickListener, diff){
    companion object {
        private val diff = object: DiffUtil.ItemCallback<VRS>(){
            override fun areContentsTheSame(oldItem: VRS, newItem: VRS): Boolean =
                oldItem.lastUpdatedDate == newItem.lastUpdatedDate

            override fun areItemsTheSame(oldItem: VRS, newItem: VRS): Boolean =
                oldItem.iD == newItem.iD

        }
    }

    override fun getItemLayoutRes(): Int = R.layout.item_vrs

    override fun getViewHolder(binding: ItemVrsBinding): VrsViewHolder =
        VrsViewHolder(binding)

}
