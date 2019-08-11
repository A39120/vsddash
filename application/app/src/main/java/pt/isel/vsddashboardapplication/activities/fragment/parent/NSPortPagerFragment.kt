package pt.isel.vsddashboardapplication.activities.fragment.parent

import android.os.Bundle
import androidx.fragment.app.FragmentPagerAdapter
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.navArgs
import pt.isel.vsddashboardapplication.R
import pt.isel.vsddashboardapplication.activities.adapter.pager.NSPortViewPagerAdapter
import pt.isel.vsddashboardapplication.activities.fragment.base.BasePagerFragment
import pt.isel.vsddashboardapplication.activities.fragment.base.IAlarmParent
import pt.isel.vsddashboardapplication.viewmodel.NSGViewModel
import pt.isel.vsddashboardapplication.viewmodel.NSPortViewModel
import pt.isel.vsddashboardapplication.viewmodel.parent.AlarmParentViewModel
import javax.inject.Inject

class NSPortPagerFragment : BasePagerFragment(), IAlarmParent{

    override fun getAlarmViewModel(): AlarmParentViewModel {
        return this.viewModel
    }

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    lateinit var viewModel : NSPortViewModel

    override fun getTitle(): Int = R.string.port

    private val args : NSPortPagerFragmentArgs by navArgs()

    override fun getPager(): FragmentPagerAdapter =
        NSPortViewPagerAdapter(this.childFragmentManager)

    fun getNsgId() = args.nsgId
    fun getPortId() = args.portId

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProviders.of(this,viewModelFactory)[NSPortViewModel::class.java]
        viewModel.init(args.portId, args.nsgId)
    }

}