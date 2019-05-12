package pt.isel.vsddashboardapplication.activities.base

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingComponent
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel

abstract class BaseFragment<T : DataBindingComponent, V : ViewModel> : Fragment() {

    private lateinit var binding: T
    private lateinit var viewModel: V

    abstract fun assignBinding(context: Context) : T
    abstract fun assingViewModel() : V


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = assingViewModel()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = assignBinding(this.context!!)
        return null
    }
}