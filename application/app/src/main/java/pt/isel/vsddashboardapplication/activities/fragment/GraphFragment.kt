package pt.isel.vsddashboardapplication.activities.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import pt.isel.vsddashboardapplication.R
import pt.isel.vsddashboardapplication.databinding.GraphFragmentBinding

class GraphFragment : Fragment() {

    private lateinit var binding : GraphFragmentBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        binding = DataBindingUtil.inflate(inflater, R.layout.graph_fragment, container, false)

        return binding.root
    }


}