package pt.isel.vsddashboardapplication.activities.adapter.viewholder

import android.view.View
import pt.isel.vsddashboardapplication.repository.pojo.NSPort
import pt.isel.vsddashboardapplication.databinding.PortItemBinding

class NSPortViewHolder(private val binding: PortItemBinding) : BindingViewHolder<NSPort>(binding) {

    override fun bind(item: NSPort, onClick: ((NSPort, View) -> Unit)?) {
        binding.port = item
        binding.portInfo.setOnClickListener { onClick?.invoke(item, binding.portInfo) }
        binding.executePendingBindings()
    }

}
