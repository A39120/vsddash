package pt.isel.vsddashboardapplication.activities.fragment

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch

import pt.isel.vsddashboardapplication.R
import pt.isel.vsddashboardapplication.VsdApplication
import pt.isel.vsddashboardapplication.activities.NsgActivity
import pt.isel.vsddashboardapplication.communication.services.NSGatewayService
import pt.isel.vsddashboardapplication.communication.services.RetrofitServices
import pt.isel.vsddashboardapplication.databinding.NsgatewayFragmentBinding
import pt.isel.vsddashboardapplication.repository.NSGatewayRepository
import pt.isel.vsddashboardapplication.repository.dao.NSGatewayDao
import pt.isel.vsddashboardapplication.repository.database.VsdDatabase
import pt.isel.vsddashboardapplication.repository.implementation.NSGatewayRepoImpl
import pt.isel.vsddashboardapplication.repository.pojo.enumerables.BootstrapStatus
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

    /**
     * Init repo and view model
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val id = (this.activity as NsgActivity).getNsgId()
        repository = NSGatewayRepoImpl(nsgService!!, dao)
        viewModel = ViewModelProviders.of(this).get(NSGViewModel::class.java)
        viewModel.init(repository, id)
    }

    /**
     * Create view binding and update data
     */
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewModel.nsg.observe(this, Observer {
            updateUI()
            changeStatusColor(it.bootstrapStatus)
            binding.executePendingBindings()
        })

        binding = DataBindingUtil.inflate( inflater, R.layout.nsgateway_fragment, container, false )
        binding.lifecycleOwner = this
        return binding.root
    }

    /**
     * Updates UI
     * TODO: Check if blocks
     */
    private fun updateUI() = launch {
        binding.vm = viewModel
        binding.executePendingBindings()
    }

    /**
     * Change the color of the status
     */
    private fun changeStatusColor(bootstrapStatus: BootstrapStatus?) {
        val color = when(bootstrapStatus){
            BootstrapStatus.ACTIVE -> R.color.green
            else -> R.color.red
        }
        binding.nsgbootstrapActive.setBackgroundColor(color)
    }

}
