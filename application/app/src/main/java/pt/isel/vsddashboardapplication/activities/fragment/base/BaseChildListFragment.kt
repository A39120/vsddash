package pt.isel.vsddashboardapplication.activities.fragment.base

import android.util.Log
import androidx.annotation.LayoutRes
import androidx.recyclerview.widget.LinearLayoutManager
import pt.isel.vsddashboardapplication.R
import pt.isel.vsddashboardapplication.databinding.FragmentListBinding

abstract class BaseChildListFragment : BaseChildFragment<FragmentListBinding>(), IRefreshableComponent {
    companion object { private const val TAG = "FRAG/BASE_LIST_CHILD" }

    /**
     * Sets up the adapter for the RecyclerView
     */
    protected abstract fun setAdapter()

    /**
     * Gets the layout res id
     */
    @LayoutRes
    override fun getLayoutRes() : Int = R.layout.fragment_list

    /**
     * Sets the binding variables
     */
    override fun setBindingObjects() {
        Log.d(TAG, "Setting up adapter (${this.javaClass})")
        setAdapter()
        binding.refreshLayout.setOnRefreshListener { refresh() }
        binding.list.layoutManager = LinearLayoutManager(this.context)
    }

}
