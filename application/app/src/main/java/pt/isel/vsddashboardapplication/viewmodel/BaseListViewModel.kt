package pt.isel.vsddashboardapplication.viewmodel

import android.util.Log
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

abstract class BaseListViewModel<T> : ViewModel() {
    companion object {
        private const val TAG = "VM/BASE"
    }

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
    fun update() {
        Log.d(TAG, "Updating ${this.javaClass} using VM scope")
        viewModelScope.launch { updateLiveData() }
    }

    /**
     * This override is only to log the changes
     */
    override fun onCleared() {
        super.onCleared()
        Log.d(TAG, "The ${this.javaClass} was cleared")
    }

}