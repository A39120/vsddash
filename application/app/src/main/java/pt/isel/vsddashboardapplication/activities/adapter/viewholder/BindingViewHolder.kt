package pt.isel.vsddashboardapplication.activities.adapter.viewholder

import android.view.View
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView

abstract class BindingViewHolder<T>(binding: ViewDataBinding)
    : RecyclerView.ViewHolder(binding.root) {

    abstract fun bind(item: T, onClick: ((T, View) -> Unit)? = null)
}