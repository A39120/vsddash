package pt.isel.vsddashboardapplication.viewmodel.parent

import androidx.lifecycle.LiveData
import pt.isel.vsddashboardapplication.model.VRS

interface VrsParentViewModel {

    fun getVrsListLiveData() : LiveData<List<VRS>?>

    suspend fun updateVrsList()

}