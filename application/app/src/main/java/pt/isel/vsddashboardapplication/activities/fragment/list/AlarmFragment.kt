package pt.isel.vsddashboardapplication.activities.fragment.list

import android.content.Context
import android.util.Log
import androidx.lifecycle.Observer
import dagger.android.support.AndroidSupportInjection
import pt.isel.vsddashboardapplication.activities.adapter.AlarmAdapter
import pt.isel.vsddashboardapplication.activities.fragment.base.BaseChildListFragment
import pt.isel.vsddashboardapplication.activities.fragment.base.IAlarmParent
import pt.isel.vsddashboardapplication.utils.RefreshState

/**
 * Fragment responsible for showing a list of Alarms
 */
class AlarmFragment : BaseChildListFragment() {
    companion object { private const val TAG = "FRAG/ALARMS" }

    private lateinit var adapter: AlarmAdapter

    override fun setAdapter() {
        this.adapter = AlarmAdapter()
        binding.list.adapter = this.adapter
    }

    /**
     * Observes the view model
     */
     override fun observeViewModel() {
        (parentFragment as IAlarmParent).getAlarmViewModel()
            .getAlarmsLiveData()
            .observe(this, Observer { adapter.setList(it) })

        (parentFragment as IAlarmParent).getAlarmViewModel()
            .getRefreshState()
            .observe(this, Observer { binding.refreshLayout.isRefreshing = it == RefreshState.INPROGRESS })
    }


    override fun onAttach(context: Context) {
        super.onAttach(context)
        Log.d(TAG, "Injecting fragment with dependencies (${this.javaClass})")
        AndroidSupportInjection.inject(this)
    }

    override fun refresh() {
        (parentFragment as IAlarmParent)
            .getAlarmViewModel()
            .updateAlarmsLiveData()
    }


}