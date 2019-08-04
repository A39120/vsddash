package pt.isel.vsddashboardapplication.viewmodel

import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import pt.isel.vsddashboardapplication.model.VSC
import pt.isel.vsddashboardapplication.model.VSP
import pt.isel.vsddashboardapplication.repository.base.VscRepository
import pt.isel.vsddashboardapplication.repository.base.VspRepository
import pt.isel.vsddashboardapplication.viewmodel.base.BaseViewModel
import javax.inject.Inject

class VspViewModel @Inject constructor(
    private val repository: VspRepository,
    private val vscRepository: VscRepository
): BaseViewModel<VSP>() {

    val vscListLiveData = MediatorLiveData<List<VSC>?>()

    override suspend fun setLiveData() {
        liveData.addSource(repository.get()){
            if(it != null && it.isNotEmpty())
                liveData.value = it.first()
        }

        vscListLiveData.addSource(
            Transformations.switchMap(liveData) { vscRepository.getAll(it.iD) }
        ) { vscListLiveData.value = it }
    }

    override suspend fun updateLiveData() {
        repository.update()
        liveData.value?.iD?.let { vscRepository.updateAll(it) }
    }

    fun init(){
        viewModelScope.launch {
            setLiveData()
            liveData.value ?: updateLiveData()
        }
    }

}