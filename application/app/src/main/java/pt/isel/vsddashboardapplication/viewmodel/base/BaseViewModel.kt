package pt.isel.vsddashboardapplication.viewmodel.base

import android.util.Log
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import pt.isel.vsddashboardapplication.utils.RefreshState

/**
 * Abstract class that will provide other view models
 */
abstract class BaseViewModel<T> : ViewModel() {
    companion object {
        private const val TAG = "VM/BASE"
    }

    val liveData = MediatorLiveData<T>()
    val refreshStateLiveData = MutableLiveData(RefreshState.NONE)

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
    fun update() = viewModelScope.launch { updateLiveData() }

    /**
     * This override is only to log the changes
     */
    override fun onCleared() {
        super.onCleared()
        Log.d(TAG, "The ${this.javaClass} was cleared")
    }
}