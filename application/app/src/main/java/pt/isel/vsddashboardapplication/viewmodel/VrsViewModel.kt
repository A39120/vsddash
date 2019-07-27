package pt.isel.vsddashboardapplication.viewmodel

import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import pt.isel.vsddashboardapplication.model.VRS
import pt.isel.vsddashboardapplication.repository.base.VrsRepository
import pt.isel.vsddashboardapplication.utils.RefreshState
import pt.isel.vsddashboardapplication.viewmodel.base.BaseViewModel
import javax.inject.Inject

class VrsViewModel @Inject constructor(
    private val repository: VrsRepository
) : BaseViewModel<VRS>() {

    override suspend fun setLiveData() {
        liveData.addSource(repository.get(id)){ liveData.value = it}
    }

    override suspend fun updateLiveData() {
        refreshStateLiveData.postValue(RefreshState.INPROGRESS)
        repository.update(id) { refreshStateLiveData.postValue(RefreshState.NONE) }
    }

    private lateinit var id: String
    fun init(id: String) {
        this.id = id
        viewModelScope.launch { setLiveData() }
    }

}
