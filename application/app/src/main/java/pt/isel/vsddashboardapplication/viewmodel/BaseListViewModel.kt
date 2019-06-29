package pt.isel.vsddashboardapplication.viewmodel

import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

abstract class BaseListViewModel<T> : ViewModel() {

    val liveData = MediatorLiveData<List<T>>()

    /**
     * Sets live data
     */
    protected abstract suspend fun setLiveData()

    /**
     * Updates live data
     */
    protected abstract suspend fun updateLiveData()

    /**
     * Updates data through the view model scope
     */
    fun update() { viewModelScope.launch { updateLiveData() } }

}