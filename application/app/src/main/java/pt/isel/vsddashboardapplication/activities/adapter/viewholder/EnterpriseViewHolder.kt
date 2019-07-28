package pt.isel.vsddashboardapplication.activities.adapter.viewholder

import android.view.View
import pt.isel.vsddashboardapplication.databinding.ItemEnterpriseBinding
import pt.isel.vsddashboardapplication.model.Enterprise

class EnterpriseViewHolder(private val binding: ItemEnterpriseBinding) : BindingViewHolder<Enterprise>(binding) {

    override fun bind(item: Enterprise, onClick: ((Enterprise, View) -> Unit)?) {
        binding.enterprise = item
        binding.cardview.setOnClickListener { view -> onClick?.invoke(item, view) }
        binding.executePendingBindings()
    }

}