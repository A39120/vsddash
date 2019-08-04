package pt.isel.vsddashboardapplication.activities.adapter.viewholder

import android.view.View
import pt.isel.vsddashboardapplication.databinding.ItemVrsBinding
import pt.isel.vsddashboardapplication.model.VRS


class VrsViewHolder(private val binding: ItemVrsBinding) : BindingViewHolder<VRS>(binding) {

    override fun bind(item: VRS, onClick: ((VRS, View) -> Unit)?) {
        binding.vrs = item
        binding.vrsCard.setOnClickListener { vrs -> onClick?.invoke(item, vrs) }
        binding.executePendingBindings()
    }

}
