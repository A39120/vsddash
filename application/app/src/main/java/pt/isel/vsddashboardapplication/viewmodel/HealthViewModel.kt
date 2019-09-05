package pt.isel.vsddashboardapplication.viewmodel

import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import pt.isel.vsddashboardapplication.model.Health
import pt.isel.vsddashboardapplication.repository.base.HealthRepository
import pt.isel.vsddashboardapplication.utils.RefreshState
import pt.isel.vsddashboardapplication.viewmodel.base.BaseListViewModel
import pt.isel.vsddashboardapplication.viewmodel.base.BaseViewModel
import javax.inject.Inject

class HealthViewModel @Inject constructor(
    private val healthRepository: HealthRepository
): BaseListViewModel<Health>() {

    override suspend fun setLiveData() {
        val ld = healthRepository.get()
        liveData.addSource(ld){
            liveData.postValue(it)
        }
        if(ld.value == null)
            healthRepository.update()
    }

    override suspend fun updateLiveData() {
        refreshStateLiveData.postValue(RefreshState.INPROGRESS)
        healthRepository.update() {
            refreshStateLiveData.postValue(RefreshState.NONE)
        }
    }

    fun init() {
        viewModelScope.launch { setLiveData() }
    }

}