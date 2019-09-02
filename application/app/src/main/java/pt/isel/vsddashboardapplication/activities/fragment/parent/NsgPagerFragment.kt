package pt.isel.vsddashboardapplication.activities.fragment.parent

import android.os.Bundle
import androidx.annotation.StringRes
import androidx.fragment.app.FragmentPagerAdapter
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.navArgs
import pt.isel.vsddashboardapplication.R
import pt.isel.vsddashboardapplication.activities.adapter.pager.NSGViewPagerAdapter
import pt.isel.vsddashboardapplication.activities.fragment.base.BasePagerFragment
import pt.isel.vsddashboardapplication.activities.fragment.base.IAlarmParent
import pt.isel.vsddashboardapplication.viewmodel.parent.AlarmParentViewModel
import pt.isel.vsddashboardapplication.viewmodel.NSGViewModel
import javax.inject.Inject

/**
 * NSG Parent fragment, it's the pager fragment responsible for
 * holding fragments that have information on the fragment
 */
class NsgPagerFragment : BasePagerFragment(), IAlarmParent{

    private val args : NsgPagerFragmentArgs by navArgs()

    fun getNsgId() = args.nsgId


    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    lateinit var viewModel : NSGViewModel

    override fun getPager(): FragmentPagerAdapter =
        NSGViewPagerAdapter(this.childFragmentManager)

    @StringRes
    override fun getTitle() = R.string.nsg

    override fun getAlarmViewModel(): AlarmParentViewModel {
        return viewModel
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProviders.of(this,viewModelFactory)[NSGViewModel::class.java]
        viewModel.init(args.nsgId)
    }

}
