package pt.isel.vsddashboardapplication.activities.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingComponent
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.LifecycleOwner

abstract class BaseViewRelated<T : ViewDataBinding> : LifecycleOwner{

    protected lateinit var binding : T

    @LayoutRes
    abstract fun layoutToInflate(): Int

    abstract fun layoutInflater() : LayoutInflater

    protected fun createView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?) {
        binding = DataBindingUtil.inflate(inflater, layoutToInflate(), container, false)
        binding.lifecycleOwner = this
    }

    protected fun setContentView() {
        //binding = DataBindingUtil.setContentView()
    }
}