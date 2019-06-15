package pt.isel.vsddashboardapplication.activities

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.UiThread
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob

import pt.isel.vsddashboardapplication.R
import pt.isel.vsddashboardapplication.VsdApplication
import pt.isel.vsddashboardapplication.communication.services.NSGatewayService
import pt.isel.vsddashboardapplication.communication.services.RetrofitServices
import pt.isel.vsddashboardapplication.databinding.NsgatewayFragmentBinding
import pt.isel.vsddashboardapplication.repository.NSGatewayRepository
import pt.isel.vsddashboardapplication.repository.dao.NSGatewayDao
import pt.isel.vsddashboardapplication.repository.database.VsdDatabase
import pt.isel.vsddashboardapplication.repository.implementation.NSGatewayRepoImpl
import pt.isel.vsddashboardapplication.repository.pojo.NSGateway
import pt.isel.vsddashboardapplication.viewmodel.NSGViewModel
import kotlin.coroutines.CoroutineContext

/**
 * Fragment that handles all information that belongs to the fragment
 */
class NSGatewayFragment : Fragment(), CoroutineScope{

    /**
     * Scope of fragment
     */
    private val job = SupervisorJob()
    override val coroutineContext: CoroutineContext = Dispatchers.Main + job

    private lateinit var viewModel: NSGViewModel
    private lateinit var binding: NsgatewayFragmentBinding
    private lateinit var repository: NSGatewayRepository

    private val dao : NSGatewayDao by lazy { VsdDatabase.getInstance(this.activity!!.applicationContext)!!.nsgDao()}
    private val nsgService : NSGatewayService? by lazy {
        RetrofitServices.getInstance().createService(
            NSGatewayService::class.java,
            (this.activity!!.application as VsdApplication).session?.APIKey!!)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
       super.onCreate(savedInstanceState)
        repository = NSGatewayRepoImpl(nsgService!!, dao)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val id = (this.activity as NsgActivity).getNsgId()

        viewModel = ViewModelProviders.of(this).get(NSGViewModel::class.java)
        viewModel.init(repository, id)
        viewModel.nsg.observe(this, Observer {
            updateUI(it)
            binding.executePendingBindings()
        })

        binding = DataBindingUtil.inflate( inflater, R.layout.nsgateway_fragment, container, false )
        binding.lifecycleOwner = this
        return binding.root
    }

    @UiThread
    private fun updateUI(nsg: NSGateway?) {
        /*nsg?.let {
            binding.nsgName.labelValue.text = it.name
            binding.nsgDesc.labelValue.text = it.description
            binding.nsgStatus.labelValue.text = it.bootstrapStatus.toString()
            binding.nsgBios.labelValue.text = it.BIOSVersion
            binding.nsgSerial.labelValue.text = it.serialNumber
            binding.nsgCpu.labelValue.text = it.CPUType
        }
        */
        binding.vm = viewModel
        binding.executePendingBindings()
    }

}
