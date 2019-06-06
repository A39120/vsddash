package pt.isel.vsddashboardapplication.activities

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil

import pt.isel.vsddashboardapplication.R
import pt.isel.vsddashboardapplication.viewmodel.NsgatewayViewModel
import pt.isel.vsddashboardapplication.databinding.NsgatewayFragmentBinding
import pt.isel.vsddashboardapplication.viewmodel.NSGViewModel

class NSGatewayFragment : Fragment() {

    companion object {
        fun newInstance() = NSGatewayFragment()
    }

    private lateinit var viewModel: NSGViewModel
    private lateinit var binding: NsgatewayFragmentBinding

    override fun onCreate(savedInstanceState: Bundle?) {
       super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewModel = ViewModelProviders.of(this).get(NSGViewModel::class.java)
        binding = DataBindingUtil.inflate<NsgatewayFragmentBinding>(
            inflater,
            R.layout.nsgateway_fragment,
            container,
            false
        )
        binding.viewmodel = viewModel


        return binding.root
    }

}
