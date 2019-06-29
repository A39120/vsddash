package pt.isel.vsddashboardapplication.activities.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import pt.isel.vsddashboardapplication.R
import pt.isel.vsddashboardapplication.activities.NsgActivity
import pt.isel.vsddashboardapplication.activities.adapter.NSPortAdapter
import pt.isel.vsddashboardapplication.databinding.ListFragmentBinding
import pt.isel.vsddashboardapplication.repository.PortRepository
import pt.isel.vsddashboardapplication.repository.database.VsdDatabase
import pt.isel.vsddashboardapplication.repository.implementation.NSPortRepositoryImpl
import pt.isel.vsddashboardapplication.viewmodel.PortListViewModel

/**
 * Fragment responsible for setting a list of ports
 */
class PortListFragment : Fragment() {

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
        repository = NSPortRepositoryImpl(dao)
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
        viewModel.liveData.observe(this, Observer{ adapter.setList(it) })
        binding.list.adapter = adapter
        binding.list.layoutManager = LinearLayoutManager(this.context)

        return binding.root
    }

}
