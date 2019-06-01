package pt.isel.vsddashboardapplication.activities

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import pt.isel.vsddashboardapplication.R

class NSGatewayFragment : Fragment() {

    companion object {
        fun newInstance() = NSGatewayFragment()
    }

    private lateinit var viewModel: NsgatewayViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.nsgateway_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(NsgatewayViewModel::class.java)
        // TODO: Use the ViewModel
    }

}
