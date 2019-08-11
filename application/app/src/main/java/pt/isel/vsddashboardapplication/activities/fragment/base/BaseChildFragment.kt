package pt.isel.vsddashboardapplication.activities.fragment.base

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import dagger.android.support.AndroidSupportInjection
import dagger.android.support.DaggerFragment

abstract class BaseChildFragment<U : ViewDataBinding> : DaggerFragment() {
    companion object { private const val TAG = "FRAG/BASE_CHILD" }

    protected lateinit var binding : U

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
     * Sets the binding variables
     */
    protected abstract fun setBindingObjects()

    /**
     * Sets the view for the fragment
     */
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        Log.d(TAG, "Creating binding for fragment (${this.javaClass})")
        this.binding = DataBindingUtil.inflate(inflater, getLayoutRes(),  container, false)
        this.binding.lifecycleOwner = this.viewLifecycleOwner

        Log.d(TAG, "Binding objects to View (${binding.javaClass})")
        setBindingObjects()

        observeViewModel()

        binding.executePendingBindings()
        return this.binding.root
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        Log.d(TAG, "Injecting fragment with dependencies (${this.javaClass})")
        AndroidSupportInjection.inject(this)
    }

}