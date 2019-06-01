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

class NSGatewayFragment : Fragment() {

    companion object {
        fun newInstance() = NSGatewayFragment()
    }

    private lateinit var viewModel: NsgatewayViewModel
    private lateinit var binding: NsgatewayFragmentBinding

    override fun onCreate(savedInstanceState: Bundle?) {
       super.onCreate(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(NsgatewayViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
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
