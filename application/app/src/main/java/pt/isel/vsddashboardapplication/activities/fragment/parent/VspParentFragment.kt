package pt.isel.vsddashboardapplication.activities.fragment.parent

import android.os.Bundle
import androidx.annotation.StringRes
import androidx.fragment.app.FragmentPagerAdapter
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import pt.isel.vsddashboardapplication.R
import pt.isel.vsddashboardapplication.activities.adapter.pager.VspViewPagerAdapter
import pt.isel.vsddashboardapplication.activities.fragment.base.BasePagerFragment
import pt.isel.vsddashboardapplication.viewmodel.VspViewModel
import javax.inject.Inject

class VspParentFragment : BasePagerFragment() {
    companion object { private const val TAG = "FRAG/VSP_PARENT" }

    @StringRes
    override fun getTitle(): Int = R.string.vsp

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    lateinit var viewModel : VspViewModel

    override fun getPager(): FragmentPagerAdapter = VspViewPagerAdapter(this.childFragmentManager)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProviders.of(this, viewModelFactory)[VspViewModel::class.java]
        viewModel.init()
    }

}
