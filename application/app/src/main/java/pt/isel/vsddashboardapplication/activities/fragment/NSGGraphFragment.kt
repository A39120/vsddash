package pt.isel.vsddashboardapplication.activities.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.androidplot.xy.BoundaryMode
import com.androidplot.xy.StepMode
import pt.isel.vsddashboardapplication.R
import pt.isel.vsddashboardapplication.databinding.GraphFragmentBinding

class NSGGraphFragment : Fragment() {

    private lateinit var binding: GraphFragmentBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.graph_fragment, container, false)

        binding.plot.run {
            setRangeBoundaries(0, 100, BoundaryMode.FIXED)
            setDomainBoundaries(0, 100, BoundaryMode.AUTO)
            //setDomainStep(StepMode.INCREMENT_BY_VAL, Number value)
        }


        return binding.root
    }

    fun add(value : Double) {
        binding.plot.setDomainStep(StepMode.INCREMENT_BY_VAL, value)
    }

    fun getValue() = Math.random() * 100

}