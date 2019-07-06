package pt.isel.vsddashboardapplication.viewmodel

import android.util.Log
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import pt.isel.vsddashboardapplication.repository.PortRepository
import pt.isel.vsddashboardapplication.model.NSPort
import javax.inject.Inject

class PortListViewModel : BaseListViewModel<NSPort>() {
    companion object{
        private const val TAG = "VM/PORTLIST"
    }

    override suspend fun setLiveData() {
        Log.d(TAG, "Setting livedata for list of ports of NSG $nsg (repository = ${repository.hashCode()})")
        repository.let { repo ->
            this.liveData.addSource(repo.getAll(nsg)) { liveData.value = it }
        }
    }

    override suspend fun updateLiveData() {
        Log.d(TAG, "Updating livedata for list of ports of NSG $nsg (repository = ${repository.hashCode()})")
        repository.updateAll(nsg)
    }


    @Inject lateinit var repository: PortRepository
    private lateinit var nsg : String

    fun init(nsgId: String){
        Log.d(TAG, "Setting parent id of NSG $nsg")
        this.nsg = nsgId
        viewModelScope.launch { setLiveData() }
    }

}
