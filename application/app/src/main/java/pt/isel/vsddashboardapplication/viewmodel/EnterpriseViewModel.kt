package pt.isel.vsddashboardapplication.viewmodel

import android.util.Log
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import pt.isel.vsddashboardapplication.model.Enterprise
import pt.isel.vsddashboardapplication.repository.EnterpriseRepository
import pt.isel.vsddashboardapplication.utils.RefreshState
import pt.isel.vsddashboardapplication.viewmodel.base.BaseListViewModel
import javax.inject.Inject

class EnterpriseViewModel @Inject constructor(private val repository: EnterpriseRepository) : BaseListViewModel<Enterprise>() {
    companion object {
        private const val TAG = "VM/ENTERPRISES"
    }

    private lateinit var userId : String

    override suspend fun setLiveData() {
        Log.d(TAG, "Setting livedata with all enterprises for user $userId (repository = ${repository.javaClass}")
        repository.let { enterpriseRepository ->
            val value = enterpriseRepository.getAll(userId)
            liveData.addSource(value){ liveData.value = it }
        }
    }

    override suspend fun updateLiveData() {
        Log.d(TAG, "Updating livedata with all enterprises for user $userId (repository = ${repository.javaClass}")
        this.refreshStateLiveData.postValue(RefreshState.INPROGRESS)
        repository.updateAll(userId) { this.refreshStateLiveData.postValue(RefreshState.NONE) }
    }

    fun init(id: String){
        Log.d(TAG, "Setting User ID = $id")
        this.userId = id
        viewModelScope.launch { setLiveData()  }
    }

}