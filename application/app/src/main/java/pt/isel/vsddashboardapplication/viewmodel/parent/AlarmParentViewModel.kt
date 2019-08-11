package pt.isel.vsddashboardapplication.viewmodel.parent

import androidx.lifecycle.LiveData
import pt.isel.vsddashboardapplication.model.Alarm
import pt.isel.vsddashboardapplication.utils.RefreshState

interface AlarmParentViewModel {

    fun getRefreshState() : LiveData<RefreshState>
    fun getAlarmsLiveData() : LiveData<List<Alarm>?>
    fun updateAlarmsLiveData()
}