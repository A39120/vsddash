package pt.isel.vsddashboardapplication.viewmodel

import android.util.Log
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import pt.isel.vsddashboardapplication.model.statistics.DpiProbestats
import pt.isel.vsddashboardapplication.repository.DpiProbestatsRepository
import pt.isel.vsddashboardapplication.repository.services.ElasticSearchRetrofitSingleton
import pt.isel.vsddashboardapplication.utils.RefreshState
import pt.isel.vsddashboardapplication.viewmodel.base.BaseViewModel
import javax.inject.Inject

/**
 * The view model responsible for getting the probestats
 */
class ProbestatsViewModel @Inject constructor(private val repository: DpiProbestatsRepository)
    : ViewModel() {
    companion object{
        private const val TAG = "VM/PROBESTATS"
    }

    private val inbound =  MediatorLiveData<List<DpiProbestats>>()
    private val outbound =  MediatorLiveData<List<DpiProbestats>>()

    private var port: String? = null
    private var nsg: String? = null
    private var minimum: Long? = null
    private var maximum: Long? = null

    private suspend fun setLiveData() {
        Log.d(TAG, "Setting livedata of probelist)")
        repository.let { repo ->
            this.inbound.addSource(repo.getInbound(port!!, nsg!!, minimum!!, maximum!!)) { inbound.value = it }
            this.outbound.addSource(repo.getOutbound(port!!, nsg!!, minimum!!, maximum!!)) { outbound.value = it }
        }
    }

    suspend fun updateLiveData() {
        viewModelScope.launch {
            Log.d(TAG, "Updating liveData from $minimum to $maximum")
            repository.update(minimum!!, maximum!!)
        }
    }

    /**
     * Sets boundaries for current LiveData;
     * @param min: Minimum timestamp of list
     * @param max: Maximum timestamp of list
     */
    fun setBoundaries(min: Long? = null, max: Long? = null){
        this.maximum = max ?: System.currentTimeMillis()
        this.minimum = min ?: this.maximum!! - 3600 * 1000
    }

    fun init(port: String, nsg: String) {
        this.port = port
        this.nsg = nsg
        setBoundaries()
        viewModelScope.launch { setLiveData() }
    }

}