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

class MonitorViewModel @Inject constructor(
    private val repository: ApmRepository,
    private val perfMonitor: PerformanceMonitorRepository
) : BaseListViewModel<APM>() {
    companion object { private const val TAG = "VM/APM" }

    private lateinit var enterpriseId : String

    val perfMonitorsLiveData = MediatorLiveData<List<PerformanceMonitor>>()

    override suspend fun setLiveData() {
        Log.d(TAG, "Setting livedata with all APMs of enterprise - $enterpriseId (repository = ${repository.javaClass}")
        val value = repository.getAll(enterpriseId)
        liveData.addSource(value){ liveData.value = it }
        if(value.value.isNullOrEmpty())
            repository.updateAll(enterpriseId)

        val perfValues = perfMonitor.getAll(enterpriseId)
        perfMonitorsLiveData.addSource(perfValues){ perfMonitorsLiveData.postValue(it) }
        if(perfValues.value.isNullOrEmpty())
            perfMonitor.updateAll(enterpriseId)
    }

    override suspend fun updateLiveData() {
        Log.d(TAG, "Updating livedata with all APMs for enterprise - $enterpriseId (repository = ${repository.javaClass}")

        this.refreshStateLiveData.postValue(RefreshState.INPROGRESS)
        repository.updateAll(enterpriseId) { this.refreshStateLiveData.postValue(RefreshState.NONE) }
    }

    fun init(id: String){
        Log.d(TAG, "Setting Enterprise ID = $id")
        this.enterpriseId = id
        viewModelScope.launch { setLiveData()  }
    }

}
