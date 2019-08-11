package pt.isel.vsddashboardapplication.activities.adapter.viewholder

import android.view.View
import pt.isel.vsddashboardapplication.databinding.ItemVportBinding
import pt.isel.vsddashboardapplication.model.VPort

class VPortViewHolder(private val binding: ItemVportBinding) : BindingViewHolder<VPort>(binding) {

    override fun bind(item: VPort, onClick: ((VPort, View) -> Unit)?) {
        binding.port = item
        binding.portInfo.setOnClickListener { vrs -> onClick?.invoke(item, vrs) }
        binding.executePendingBindings()
    }

}
