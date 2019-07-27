package pt.isel.vsddashboardapplication.viewmodel

import android.util.Log
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import pt.isel.vsddashboardapplication.repository.base.PortRepository
import pt.isel.vsddashboardapplication.model.NSPort
import pt.isel.vsddashboardapplication.utils.RefreshState
import pt.isel.vsddashboardapplication.viewmodel.base.BaseListViewModel
import javax.inject.Inject

class PortListViewModel @Inject constructor(private val repository: PortRepository): BaseListViewModel<NSPort>() {
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
        this.refreshStateLiveData.postValue(RefreshState.INPROGRESS)
        repository.updateAll(nsg) {
            this.refreshStateLiveData.postValue(RefreshState.NONE)
        }
    }

    private lateinit var nsg : String

    fun init(nsgId: String){
        this.nsg = nsgId
        viewModelScope.launch { setLiveData() }
    }

}

