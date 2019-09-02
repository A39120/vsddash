package pt.isel.vsddashboardapplication.viewmodel

import android.util.Log
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import pt.isel.vsddashboardapplication.model.Enterprise
import pt.isel.vsddashboardapplication.repository.base.EnterpriseRepository
import pt.isel.vsddashboardapplication.utils.RefreshState
import pt.isel.vsddashboardapplication.viewmodel.base.BaseListViewModel
import javax.inject.Inject

class EnterpriseViewModel @Inject constructor(private val repository: EnterpriseRepository) : BaseListViewModel<Enterprise>() {
    companion object {
        private const val TAG = "VM/ENTERPRISES"
    }

    override suspend fun setLiveData() {
        Log.d(TAG, "Setting livedata with all enterprises for current user (repository = ${repository.javaClass}")
        repository.let { enterpriseRepository ->
            val value = enterpriseRepository.getAll("")
            liveData.addSource(value){ liveData.value = it }
            if(value.value.isNullOrEmpty())
                enterpriseRepository.updateAll("")
        }
    }

    override suspend fun updateLiveData() {
        Log.d(TAG, "Updating liveData with all enterprises for current user (repository = ${repository.javaClass}")
        this.refreshStateLiveData.postValue(RefreshState.INPROGRESS)
        repository.updateAll("") { this.refreshStateLiveData.postValue(RefreshState.NONE) }
    }

    fun init(id: String, vsdId: String, organization: String){
        Log.d(TAG, "Setting User ID = $id")
        repository.setup(id, vsdId, organization)
        viewModelScope.launch { setLiveData()  }
    }

}