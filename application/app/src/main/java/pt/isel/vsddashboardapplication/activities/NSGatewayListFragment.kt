package pt.isel.vsddashboardapplication.activities

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import pt.isel.vsddashboardapplication.R
import pt.isel.vsddashboardapplication.activities.adapter.NSGatewayAdapter
import pt.isel.vsddashboardapplication.viewmodel.AllNSGatewayViewModel
import pt.isel.vsddashboardapplication.databinding.GatewayGridFragmentBinding
import pt.isel.vsddashboardapplication.repository.NSGatewayRepoImpl

class NSGatewayListFragment : Fragment() {

    private lateinit var viewModel : AllNSGatewayViewModel
    private lateinit var binding : GatewayGridFragmentBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        super.onCreateView(inflater, container, savedInstanceState)

        viewModel = AllNSGatewayViewModel()

        viewModel.init(NSGatewayRepoImpl())
        binding = DataBindingUtil.inflate(inflater, R.layout.gateway_grid_fragment, container, false)
        binding.lifecycleOwner = this

        // Start ada+ter
        val adapter = NSGatewayAdapter(ArrayList(), {})
        adapter.setList(viewModel.gateways.value)
        binding.list.adapter = adapter
        binding.list.layoutManager = LinearLayoutManager(this.context)

        viewModel.gateways.observe(this, Observer{ adapter.setList(it) })

        return binding.root
    }



}