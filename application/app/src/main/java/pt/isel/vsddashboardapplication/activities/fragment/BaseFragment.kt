package pt.isel.vsddashboardapplication.activities.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import pt.isel.vsddashboardapplication.activities.fragment.base.BaseChildFragment
import pt.isel.vsddashboardapplication.activities.fragment.base.IViewModelOwner
import javax.inject.Inject

/**
 * Base fragment that contains a view model, this fragment works by itself
 * this means it doesn't have a parent
 * @param T: the view model that belongs to the fragment
 * @param U: binding attached to the fragment
 */
abstract class BaseFragment<T: ViewModel, U : ViewDataBinding> : BaseChildFragment<U>(),
    IViewModelOwner<T> {
    companion object { private const val TAG = "FRAG/BASE" }

    val viewModel : T by lazy { assignViewModel() }

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(TAG, "Creating fragment and initiating View Model")
        initViewModel()
    }

    /**
     * Sets the view for the fragment
     */
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val root = super.onCreateView(inflater, container, savedInstanceState)
        Log.d(TAG, "Observing View Model (${viewModel.javaClass})")
        observeViewModel()
        return root
    }

}