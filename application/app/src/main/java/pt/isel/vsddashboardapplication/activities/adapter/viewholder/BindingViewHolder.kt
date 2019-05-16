package pt.isel.vsddashboardapplication.activities.adapter.viewholder

import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView

abstract class BindingViewHolder<T>(private val binding: ViewDataBinding)
    : RecyclerView.ViewHolder(binding.root) {

    abstract fun bind(item: T)
}