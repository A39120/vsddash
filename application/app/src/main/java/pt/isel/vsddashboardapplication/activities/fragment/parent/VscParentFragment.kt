package pt.isel.vsddashboardapplication.activities.fragment.parent

import android.os.Bundle
import androidx.fragment.app.FragmentPagerAdapter
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import pt.isel.vsddashboardapplication.activities.adapter.pager.VscViewPagerAdapter
import pt.isel.vsddashboardapplication.activities.fragment.base.BasePagerFragment
import pt.isel.vsddashboardapplication.viewmodel.VscViewModel
import javax.inject.Inject

/**
 * VSC Parent fragment, contains a pager with the information on
 **** VRSs - list of vrss
 **** Alarms - the alarms on the VSC
 */
class VscParentFragment : BasePagerFragment() {
    companion object { private const val TAG = "FRAG/VSC_PARENT" }

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    lateinit var viewModel : VscViewModel

    override fun getPager(): FragmentPagerAdapter = VscViewPagerAdapter(this.childFragmentManager)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProviders.of(this, viewModelFactory)[VscViewModel::class.java]
    }


}
