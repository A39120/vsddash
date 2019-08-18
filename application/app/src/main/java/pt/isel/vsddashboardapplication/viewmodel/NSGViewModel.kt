package pt.isel.vsddashboardapplication.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import pt.isel.vsddashboardapplication.model.Alarm
import pt.isel.vsddashboardapplication.model.NSGInfo
import pt.isel.vsddashboardapplication.model.NSGateway
import pt.isel.vsddashboardapplication.model.NSPort
import pt.isel.vsddashboardapplication.repository.base.NSGatewayRepository
import pt.isel.vsddashboardapplication.repository.base.NSPortRepository
import pt.isel.vsddashboardapplication.utils.RefreshState
import pt.isel.vsddashboardapplication.viewmodel.base.BaseViewModel
import pt.isel.vsddashboardapplication.viewmodel.parent.AlarmParentViewModel
import javax.inject.Inject

/**
 * NSG View Model
 */
class NSGViewModel @Inject constructor(
    private val repository: NSGatewayRepository,
    private val NSPortRepository: NSPortRepository
) : BaseViewModel<NSGateway>(), AlarmParentViewModel {
    companion object { private const val TAG = "VM/NSG" }

    override fun getRefreshState(): LiveData<RefreshState> = this.alarmsRefreshStateLiveData

    override fun getAlarmsLiveData(): LiveData<List<Alarm>?> = alarmsLiveData

    /**
     * Alarm related live data
     */
    private val alarmsRefreshStateLiveData = MediatorLiveData<RefreshState>()
    private val alarmsLiveData = MediatorLiveData<List<Alarm>?>()

    val nsginfo = MediatorLiveData<NSGInfo>()
    val portsLiveData = MediatorLiveData<List<NSPort>?>()

    override suspend fun setLiveData() {
        Log.d(TAG, "Setting liveData with NSG with ID: $id (repository = ${repository.javaClass}")
        repository.let {repo ->
            this.liveData.addSource(repo.get(id)) { liveData.value = it }
            if(liveData.value == null)
                repo.update(id)
        }

        nsginfo.addSource(Transformations.switchMap(liveData) {
            val ld = repository.getNsgInfo(it.ID)
            if(ld.value == null) updateNsgInfo()
            ld
        }) {
            nsginfo.value = it

        }

        alarmsLiveData.addSource( Transformations.switchMap(liveData) {
            val ld = repository.getAlarms(it.ID)
            if(ld.value == null) updateAlarmsLiveData()
            ld
        }) {
            alarmsLiveData.value = it
        }

        portsLiveData.addSource( Transformations.switchMap(liveData) {
            val ld = NSPortRepository.getAll(it.ID)
            if(ld.value == null) updateAlarmsLiveData()
            ld
        } )  {
            portsLiveData.value = it
        }
    }

    override suspend fun updateLiveData() {
        Log.d(TAG, "Updating liveData with NSG with ID: $id (repository = ${repository.javaClass}")
        this.refreshStateLiveData.postValue(RefreshState.INPROGRESS)
        this.repository.update(id) {
            this.refreshStateLiveData.postValue(RefreshState.NONE)
        }
    }

    /**
     * Updates the Alarms Live Data
     */
    override fun updateAlarmsLiveData(){
        viewModelScope.launch {
            Log.d(TAG, "Updating alarms livedata")
            alarmsRefreshStateLiveData.postValue(RefreshState.INPROGRESS)
            repository.updateAlarms(id) {
                alarmsRefreshStateLiveData.postValue(RefreshState.NONE)
            }
        }
    }

    /**
     * Updates the Alarms Live Data
     */
    fun updateNsgInfo(){
        viewModelScope.launch {
            Log.d(TAG, "Updating NSG Info livedata")
            refreshStateLiveData.postValue(RefreshState.INPROGRESS)
            repository.updateNsgInfo(id) { refreshStateLiveData.postValue(RefreshState.NONE) }
        }
    }

    /**
     * Updates the Alarms Live Data
     */
    fun updateNsgPorts(){
        viewModelScope.launch {
            Log.d(TAG, "Updating alarms livedata")
            refreshStateLiveData.postValue(RefreshState.INPROGRESS)
            NSPortRepository.updateAll(id) { refreshStateLiveData.postValue(RefreshState.NONE) }
        }
    }

    private lateinit var id: String

    /**
     * Initiates the view model, by extracting the data;
     */
    fun init(id: String){
        Log.d(TAG, "Setting NSG ID: $id (repository = ${repository.javaClass}")
        this.id = id
        viewModelScope.launch { setLiveData() }
    }

}






