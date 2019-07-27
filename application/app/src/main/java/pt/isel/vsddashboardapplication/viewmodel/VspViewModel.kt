package pt.isel.vsddashboardapplication.viewmodel

import pt.isel.vsddashboardapplication.model.VSP
import pt.isel.vsddashboardapplication.repository.base.VspRepository
import pt.isel.vsddashboardapplication.viewmodel.base.BaseViewModel
import javax.inject.Inject

class VspViewModel @Inject constructor(
    private val repository: VspRepository
): BaseViewModel<VSP>() {

    override suspend fun setLiveData() {
        liveData.addSource(repository.get()){
            liveData.value = it.first()
        }
    }

    override suspend fun updateLiveData() {
        repository.update()
    }

}