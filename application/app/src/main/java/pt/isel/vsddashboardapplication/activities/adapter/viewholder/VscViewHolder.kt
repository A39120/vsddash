package pt.isel.vsddashboardapplication.activities.adapter.viewholder

import android.view.View
import pt.isel.vsddashboardapplication.databinding.ItemGatewayBinding
import pt.isel.vsddashboardapplication.databinding.ItemVscBinding
import pt.isel.vsddashboardapplication.model.NSGateway
import pt.isel.vsddashboardapplication.model.VSC

class VscViewHolder(private val binding: ItemVscBinding) : BindingViewHolder<VSC>(binding) {

    override fun bind(item: VSC, onClick: ((VSC, View) -> Unit)?) {
        binding.vsc = item
        binding.vscCard.setOnClickListener { onClick?.invoke(item, it) }
        binding.executePendingBindings()
    }

}
