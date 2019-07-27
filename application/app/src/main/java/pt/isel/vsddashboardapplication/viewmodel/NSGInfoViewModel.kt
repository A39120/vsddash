package pt.isel.vsddashboardapplication.viewmodel

import android.util.Log
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import pt.isel.vsddashboardapplication.model.NSGInfo
import pt.isel.vsddashboardapplication.repository.base.NSGinfoRepository
import pt.isel.vsddashboardapplication.utils.RefreshState
import pt.isel.vsddashboardapplication.viewmodel.base.BaseViewModel
import javax.inject.Inject

class NSGInfoViewModel @Inject constructor(private val repository: NSGinfoRepository): BaseViewModel<NSGInfo>(){
    companion object { private const val TAG = "VM/NSGINFO"}

    private lateinit var id: String

    override suspend fun setLiveData() {
        Log.d(TAG, "Setting liveData with NSG with ID: $id (repository = ${repository.javaClass}")
        repository.let {repo -> this.liveData.addSource(repo.get(id)) { liveData.value = it } }

    }

    override suspend fun updateLiveData() {
        Log.d(TAG, "Updating liveData with NSG with ID: $id (repository = ${repository.javaClass}")
        this.refreshStateLiveData.postValue(RefreshState.INPROGRESS)
        this.repository.update(id) {
            this.refreshStateLiveData.postValue(RefreshState.NONE)
        }
    }

    fun init(id: String){
        this.id = id
        viewModelScope.launch { setLiveData() }
    }

}