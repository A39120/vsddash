package pt.isel.vsddashboardapplication.activities.fragment.base

import pt.isel.vsddashboardapplication.viewmodel.parent.VrsParentViewModel

interface IVRSParent {

    fun getVrsViewModel() : VrsParentViewModel
}