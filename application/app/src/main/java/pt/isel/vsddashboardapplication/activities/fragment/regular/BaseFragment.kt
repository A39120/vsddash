package pt.isel.vsddashboardapplication.activities.fragment.regular

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import dagger.android.support.AndroidSupportInjection
import dagger.android.support.DaggerFragment
import javax.inject.Inject

abstract class BaseFragment<T: ViewModel, U : ViewDataBinding> : DaggerFragment() {
    companion object {
        private const val TAG = "FRAG/BASE"
    }

    protected val viewModel : T by lazy { assignViewModel() }
    protected lateinit var binding : U

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    /**
     * Assigns the View model
     */
    protected abstract fun assignViewModel() : T

    /**
     * Gets the layout res id
     */
    @LayoutRes
    protected abstract fun getLayoutRes() : Int

    /**
     * Observes the view model for changes
     */
    protected abstract fun observeViewModel()

    /**
     * Sets the view model with the proper initiation
     */
    protected abstract fun initViewModel()

    /**
     * Sets the binding variables
     */
    protected abstract fun setBindingObjects()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(TAG, "Creating fragment and initiating View Model")
        initViewModel()
    }

    /**
     * Sets the view for the fragment
     */
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        Log.d(TAG, "Creating binding for fragment (${this.javaClass})")
        this.binding = DataBindingUtil.inflate(inflater, getLayoutRes(),  container, false)
        this.binding.lifecycleOwner = this.viewLifecycleOwner

        Log.d(TAG, "Observing View Model (${viewModel.javaClass})")
        observeViewModel()

        Log.d(TAG, "Binding objects to View (${binding.javaClass})")
        setBindingObjects()
        return this.binding.root
    }

    override fun onAttach(context: Context) {
        Log.d(TAG, "Injecting fragment with dependencies (${this.javaClass})")
        AndroidSupportInjection.inject(this)
        super.onAttach(context)
    }

}