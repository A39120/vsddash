package pt.isel.vsddashboardapplication.activities.adapter.viewholder

import android.view.View
import pt.isel.vsddashboardapplication.databinding.EnterpriseItemBinding
import pt.isel.vsddashboardapplication.model.Enterprise

class EnterpriseViewHolder(private val binding: EnterpriseItemBinding) : BindingViewHolder<Enterprise>(binding) {

    override fun bind(item: Enterprise, onClick: ((Enterprise, View) -> Unit)?) {
        binding.enterprise = item
        binding.cardview.setOnClickListener { view -> onClick?.invoke(item, view) }
        binding.executePendingBindings()
    }

}