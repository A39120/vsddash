package pt.isel.vsddashboardapplication.activities.fragment.parent

import android.os.Bundle
import androidx.annotation.StringRes
import androidx.fragment.app.FragmentPagerAdapter
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.navArgs
import pt.isel.vsddashboardapplication.R
import pt.isel.vsddashboardapplication.activities.adapter.pager.VrsViewPagerAdapter
import pt.isel.vsddashboardapplication.activities.adapter.pager.VscViewPagerAdapter
import pt.isel.vsddashboardapplication.activities.fragment.base.BasePagerFragment
import pt.isel.vsddashboardapplication.activities.fragment.base.IAlarmParent
import pt.isel.vsddashboardapplication.viewmodel.VrsViewModel
import pt.isel.vsddashboardapplication.viewmodel.VscViewModel
import pt.isel.vsddashboardapplication.viewmodel.parent.AlarmParentViewModel
import javax.inject.Inject

/**
 * VSC Parent fragment, contains a pager with the information on
 **** VRSs - list of vrss
 **** Alarms - the alarms on the VSC
 */
class VrsParentFragment : BasePagerFragment(), IAlarmParent {

    @StringRes
    override fun getTitle(): Int = R.string.vrs
    private val args :  VrsParentFragmentArgs by navArgs()

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    lateinit var viewModel : VrsViewModel

    override fun getPager(): FragmentPagerAdapter = VrsViewPagerAdapter(this.childFragmentManager)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProviders.of(this, viewModelFactory)[VrsViewModel::class.java]
        viewModel.init(args.vrsId)
    }

    override fun getAlarmViewModel(): AlarmParentViewModel {
        return viewModel
    }

    fun getVrsId() = args.vrsId
}
