package pt.isel.vsddashboardapplication.activities.fragment

import androidx.lifecycle.ViewModel
import androidx.recyclerview.widget.LinearLayoutManager
import pt.isel.vsddashboardapplication.R
import pt.isel.vsddashboardapplication.databinding.ListFragmentBinding

/**
 * Base for displaying lists
 */
abstract class BaseListFragment<T : ViewModel> : BaseFragment<T, ListFragmentBinding>() {

    override fun getLayoutRes(): Int = R.layout.list_fragment

    protected abstract fun setAdapter()

    override fun setBindingObjects() {
        setAdapter()
        binding.list.layoutManager = LinearLayoutManager(this.context)
    }



}
