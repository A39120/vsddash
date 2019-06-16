package pt.isel.vsddashboardapplication.activities.fragment

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager

import pt.isel.vsddashboardapplication.R
import pt.isel.vsddashboardapplication.VsdApplication
import pt.isel.vsddashboardapplication.activities.NsgActivity
import pt.isel.vsddashboardapplication.activities.adapter.NSPortAdapter
import pt.isel.vsddashboardapplication.communication.services.NSPortServices
import pt.isel.vsddashboardapplication.communication.services.RetrofitServices
import pt.isel.vsddashboardapplication.repository.PortRepository
import pt.isel.vsddashboardapplication.repository.database.VsdDatabase
import pt.isel.vsddashboardapplication.viewmodel.PortListViewModel
import pt.isel.vsddashboardapplication.databinding.ListFragmentBinding
import pt.isel.vsddashboardapplication.repository.implementation.NSPortRepoImpl

class PortListFragment : Fragment() {
    companion object { fun newInstance() = PortListFragment() }

    private val viewModel: PortListViewModel by lazy {
        ViewModelProviders.of(this).get(PortListViewModel::class.java)
    }

    private lateinit var repository: PortRepository
    private lateinit var binding: ListFragmentBinding
    private lateinit var nsgId : String

    override fun onCreate(savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)
        nsgId = (this.activity as NsgActivity).getNsgId()
        val dao by lazy {  VsdDatabase.getInstance(this.activity!!.applicationContext)!!.nsportDao() }
        val service = RetrofitServices.getInstance().createService(
            NSPortServices::class.java,
            (this.activity!!.application as VsdApplication).session?.APIKey!! )
        repository = NSPortRepoImpl(dao, service!!)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.list_fragment, container, false)

        // Start adapter
        val adapter = NSPortAdapter { _, _ ->}

        viewModel.init(repository, nsgId)
        viewModel.portList.observe(this, Observer{ adapter.setList(it) })
        binding.list.adapter = adapter
        binding.list.layoutManager = LinearLayoutManager(this.context)

        return binding.root
    }

}
