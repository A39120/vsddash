package pt.isel.vsddashboardapplication.activities

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import pt.isel.vsddashboardapplication.R
import pt.isel.vsddashboardapplication.VsdApplication
import pt.isel.vsddashboardapplication.activities.adapter.NSGatewayAdapter
import pt.isel.vsddashboardapplication.communication.services.NSGatewayService
import pt.isel.vsddashboardapplication.communication.services.RetrofitServices
import pt.isel.vsddashboardapplication.viewmodel.AllNSGatewayViewModel
import pt.isel.vsddashboardapplication.databinding.GatewayGridFragmentBinding
import pt.isel.vsddashboardapplication.repository.NSGatewayRepoImpl
import pt.isel.vsddashboardapplication.repository.NSGatewayRepository
import pt.isel.vsddashboardapplication.repository.dao.NSGatewayDao
import pt.isel.vsddashboardapplication.repository.database.VsdDatabase

class NSGatewayListFragment : Fragment() {

    private lateinit var repo: NSGatewayRepository
    private lateinit var viewModel : AllNSGatewayViewModel
    private lateinit var binding : GatewayGridFragmentBinding

    private val dao : NSGatewayDao by lazy { VsdDatabase.getInstance(this.activity!!.applicationContext)!!.nsgDao()}
    private val nsgService : NSGatewayService? by lazy {
        //TODO: Fix this in case the session goes under
        RetrofitServices.getInstance().createService<NSGatewayService>(
            NSGatewayService::class.java,
            (this.activity!!.application as VsdApplication).session?.apiKey!!)

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        repo = NSGatewayRepoImpl(nsgService!!, dao)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        super.onCreateView(inflater, container, savedInstanceState)

        val enterprise = (this.activity!!.application as VsdApplication)
            .session!!
            .enterpriseId!!

        viewModel = AllNSGatewayViewModel()

        viewModel.init(repo, enterprise)
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