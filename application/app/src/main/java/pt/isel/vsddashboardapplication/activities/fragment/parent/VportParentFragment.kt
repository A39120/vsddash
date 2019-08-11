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
import pt.isel.vsddashboardapplication.viewmodel.VPortViewModel
import pt.isel.vsddashboardapplication.viewmodel.parent.AlarmParentViewModel
import javax.inject.Inject

class VportParentFragment : BasePagerFragment(), IAlarmParent {

    override fun getAlarmViewModel(): AlarmParentViewModel { return this.viewModel }

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    lateinit var viewModel : VPortViewModel

    override fun getTitle(): Int = R.string.port

    private val args : VportParentFragmentArgs by navArgs()

    override fun getPager(): FragmentPagerAdapter =
        NSPortViewPagerAdapter(this.childFragmentManager)

    fun getVrsId() = args.vrsId
    fun getVportId() = args.vportId

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProviders.of(this,viewModelFactory)[VPortViewModel::class.java]
        viewModel.init(args.vportId, args.vrsId)
    }

}