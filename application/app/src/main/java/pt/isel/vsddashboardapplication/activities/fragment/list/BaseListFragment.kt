package pt.isel.vsddashboardapplication.activities.fragment.list

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.recyclerview.widget.LinearLayoutManager
import pt.isel.vsddashboardapplication.R
import pt.isel.vsddashboardapplication.activities.fragment.regular.BaseFragment
import pt.isel.vsddashboardapplication.databinding.ListFragmentBinding

/**
 * Base for displaying lists
 */
abstract class BaseListFragment<T : ViewModel> : BaseFragment<T, ListFragmentBinding>() {
    companion object {
        private const val TAG = "FRAG/BASELIST"
    }

    override fun getLayoutRes(): Int = R.layout.list_fragment

    protected abstract fun setAdapter()

    override fun setBindingObjects() {
        Log.d(TAG, "Setting up adapter")
        setAdapter()
        binding.list.layoutManager = LinearLayoutManager(this.context)
    }



}
