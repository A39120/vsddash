package pt.isel.vsddashboardapplication.viewmodel

import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import pt.isel.vsddashboardapplication.model.VSC
import pt.isel.vsddashboardapplication.model.VSP
import pt.isel.vsddashboardapplication.repository.base.VscRepository
import pt.isel.vsddashboardapplication.repository.base.VspRepository
import pt.isel.vsddashboardapplication.utils.RefreshState
import pt.isel.vsddashboardapplication.viewmodel.base.BaseViewModel
import javax.inject.Inject

class VspViewModel @Inject constructor(
    private val repository: VspRepository,
    private val vscRepository: VscRepository
): BaseViewModel<VSP>() {

    val vscListLiveData = MediatorLiveData<List<VSC>?>()

    override suspend fun setLiveData() {
        val ld = repository.get()

        if(ld.value == null)
            repository.update()

        liveData.addSource(ld){
            if(!it.isNullOrEmpty())
                liveData.value = it.first()
        }

        vscListLiveData.addSource(
            Transformations.switchMap(liveData) {
                val vsc = vscRepository.getAll(it.iD)
                if(vsc.value.isNullOrEmpty())
                    viewModelScope.launch { vscRepository.updateAll(it.iD) }
                vsc
            }
        ) { vscListLiveData.value = it }
    }

    override suspend fun updateLiveData() {
        refreshStateLiveData.postValue(RefreshState.INPROGRESS)
        repository.update {
            refreshStateLiveData.postValue(RefreshState.NONE)
        }
    }

    fun updateVscLiveData(){
        viewModelScope.launch {
            refreshStateLiveData.postValue(RefreshState.INPROGRESS)
            liveData.value?.iD?.let {
                vscRepository.updateAll(it) {
                    refreshStateLiveData.postValue(RefreshState.NONE)
                }
            }
        }
    }

    fun init(){
        viewModelScope.launch { setLiveData() }
    }

}