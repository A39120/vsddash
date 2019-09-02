package pt.isel.vsddashboardapplication.activities.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import pt.isel.vsddashboardapplication.activities.adapter.viewholder.BindingViewHolder
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil


abstract class BaseAdapter<T, U : BindingViewHolder<T>, V : ViewDataBinding>(
    private val onClickListener: ((T, View) -> Unit)? = null,
    diff : DiffUtil.ItemCallback<T>
) : RecyclerView.Adapter<U>() {

    private val mDiffer : AsyncListDiffer<T> = AsyncListDiffer(this, diff)

    override fun getItemCount(): Int = mDiffer.currentList.size

    fun setList(list: List<T>?){
        mDiffer.submitList(list?:listOf())
    }

    @LayoutRes
    abstract fun getItemLayoutRes() : Int

    abstract fun getViewHolder(binding : V) : U

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): U {
        val inflater = LayoutInflater.from(parent.context)
        val binding = DataBindingUtil.inflate<V>(inflater, getItemLayoutRes(), parent, false)
        return getViewHolder(binding)
    }

    override fun onBindViewHolder(holder: U, position: Int) {
        val item = mDiffer.currentList[position]
        holder.bind(item, onClickListener)
    }

}