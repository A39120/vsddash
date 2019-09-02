package pt.isel.vsddashboardapplication.viewmodel

import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import pt.isel.vsddashboardapplication.model.Health
import pt.isel.vsddashboardapplication.repository.base.HealthRepository
import pt.isel.vsddashboardapplication.viewmodel.base.BaseViewModel
import javax.inject.Inject

class HealthViewModel @Inject constructor(
    private val healthRepository: HealthRepository
): BaseViewModel<Health>() {

    override suspend fun setLiveData() {
        liveData.addSource(healthRepository.get()){ liveData.postValue(it) }
    }

    override suspend fun updateLiveData() { healthRepository.update() }

    fun init() {
        viewModelScope.launch { setLiveData() }
    }

}