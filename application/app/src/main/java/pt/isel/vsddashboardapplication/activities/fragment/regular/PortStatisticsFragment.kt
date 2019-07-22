package pt.isel.vsddashboardapplication.activities.fragment.regular

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import dagger.android.support.AndroidSupportInjection
import dagger.android.support.DaggerFragment
import pt.isel.vsddashboardapplication.R
import pt.isel.vsddashboardapplication.databinding.PortStatisticsFragmentBinding

class PortStatisticsFragment : DaggerFragment() {
    companion object {
        private const val TAG = "FRAG/PORT_STATS"
    }

    private lateinit var binding : PortStatisticsFragmentBinding

    /**
     * Sets the view for the fragment
     */
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        Log.d(TAG, "Creating binding for fragment (${this.javaClass})")
        this.binding = DataBindingUtil.inflate(inflater, R.layout.port_statistics_fragment,  container, false)
        this.binding.lifecycleOwner = this.viewLifecycleOwner
        binding.executePendingBindings()
        return this.binding.root
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        Log.d(TAG, "Injecting fragment with dependencies (${this.javaClass})")
        AndroidSupportInjection.inject(this)
    }

}