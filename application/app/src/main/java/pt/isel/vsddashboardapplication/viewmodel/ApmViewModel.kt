package pt.isel.vsddashboardapplication.viewmodel

import android.util.Log
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import pt.isel.vsddashboardapplication.model.APM
import pt.isel.vsddashboardapplication.repository.base.ApmRepository
import pt.isel.vsddashboardapplication.utils.RefreshState
import pt.isel.vsddashboardapplication.viewmodel.base.BaseListViewModel
import javax.inject.Inject

class ApmViewModel @Inject constructor(private val repository: ApmRepository) : BaseListViewModel<APM>() {
    companion object { private const val TAG = "VM/APM" }

    private lateinit var enterpriseId : String

    override suspend fun setLiveData() {
        Log.d(TAG, "Setting livedata with all APMs of enterprise - $enterpriseId (repository = ${repository.javaClass}")
        repository.let { enterpriseRepository ->
            val value = enterpriseRepository.getAll(enterpriseId)
            liveData.addSource(value){ liveData.value = it }
            if(value.value.isNullOrEmpty())
                repository.updateAll(enterpriseId)
        }
    }

    override suspend fun updateLiveData() {
        Log.d(TAG, "Updating livedata with all APMs for enterprise - $enterpriseId (repository = ${repository.javaClass}")

        this.refreshStateLiveData.postValue(RefreshState.INPROGRESS)
        repository.updateAll(enterpriseId) { this.refreshStateLiveData.postValue(RefreshState.NONE) }
    }

    fun init(id: String){
        Log.d(TAG, "Setting User ID = $id")
        this.enterpriseId = id
        viewModelScope.launch { setLiveData()  }
    }

}
