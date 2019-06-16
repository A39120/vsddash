package pt.isel.vsddashboardapplication.activities.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import pt.isel.vsddashboardapplication.R
import pt.isel.vsddashboardapplication.activities.adapter.viewholder.BindingViewHolder

abstract class BaseAdapter<T, U : BindingViewHolder<T>, V : ViewDataBinding>(
    private val onClickListener: ((T, View) -> Unit)? = null
) : RecyclerView.Adapter<U>() {

    private val values: ArrayList<T> = ArrayList()
    override fun getItemCount(): Int = values.size

    @LayoutRes
    abstract fun getItemLayoutRes() : Int

    abstract fun getViewHolder(binding : V) : U

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): U {
        val inflater = LayoutInflater.from(parent.context)
        val binding = DataBindingUtil.inflate<V>(inflater, getItemLayoutRes(), parent, false)
        return getViewHolder(binding)
    }

    override fun onBindViewHolder(holder: U, position: Int) {
        val item = values[position]
        holder.bind(item, onClickListener)
        holder.itemView
    }

    fun setList(list: List<T>?){
        list?.run {
            val newValues = this.filter { new -> !this@BaseAdapter.values.contains(new) }
            this@BaseAdapter.values.removeIf { old -> !this.contains(old) }
            newValues.forEach {
                this@BaseAdapter.values.add(it)
            }
        }

        notifyDataSetChanged()
    }

}