package pt.isel.vsddashboardapplication.activities.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.ViewModel
import dagger.android.support.DaggerFragment

abstract class BaseFragment<T: ViewModel, U : ViewDataBinding> : DaggerFragment() {

    protected val viewModel : T by lazy { assignViewModel() }
    protected lateinit var binding : U

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
        initViewModel()
    }

    /**
     * Sets the view for the fragment
     */
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        super.onCreateView(inflater, container, savedInstanceState)

        this.binding = DataBindingUtil.inflate(inflater, getLayoutRes(),  container, false)
        this.binding.lifecycleOwner = this.viewLifecycleOwner

        observeViewModel()

        setBindingObjects()

        return this.binding.root
    }
}