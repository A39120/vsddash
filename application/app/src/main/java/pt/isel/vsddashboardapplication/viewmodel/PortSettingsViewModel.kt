package pt.isel.vsddashboardapplication.viewmodel

import android.util.Log
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import pt.isel.vsddashboardapplication.model.APM
import pt.isel.vsddashboardapplication.model.PerformanceMonitor
import pt.isel.vsddashboardapplication.repository.base.ApmRepository
import pt.isel.vsddashboardapplication.repository.base.PerformanceMonitorRepository
import pt.isel.vsddashboardapplication.utils.RefreshState
import pt.isel.vsddashboardapplication.viewmodel.base.BaseListViewModel
import javax.inject.Inject

class PortSettingsViewModel @Inject constructor(
    private val repository: ApmRepository,
    private val performanceMonitorRepository: PerformanceMonitorRepository
) : BaseListViewModel<APM>() {
    companion object { private const val TAG = "VM/PORT_SETTINGS" }

    private lateinit var enterpriseId : String

    private val performanceMonitor =  MediatorLiveData<List<PerformanceMonitor>?>()

    override suspend fun setLiveData() {
        Log.d(TAG, "Setting livedata with all APMs of enterprise - $enterpriseId (repository = ${repository.javaClass}")
        val value = repository.getAll(enterpriseId)
        liveData.addSource(value){ liveData.value = it }
        if(value.value.isNullOrEmpty())
            repository.updateAll(enterpriseId)

        val perfMonitor = performanceMonitorRepository.getAll(enterpriseId)
        performanceMonitor.addSource(perfMonitor) { performanceMonitor.value = it }
        if(perfMonitor.value.isNullOrEmpty())
            performanceMonitorRepository.updateAll(enterpriseId)
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
