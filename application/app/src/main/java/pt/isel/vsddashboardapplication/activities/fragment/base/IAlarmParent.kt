package pt.isel.vsddashboardapplication.activities.fragment.base

import pt.isel.vsddashboardapplication.viewmodel.parent.AlarmParentViewModel

interface IAlarmParent {

    fun getAlarmViewModel() : AlarmParentViewModel

}