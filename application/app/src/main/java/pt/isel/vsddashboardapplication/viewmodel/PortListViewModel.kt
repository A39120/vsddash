package pt.isel.vsddashboardapplication.viewmodel

import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import pt.isel.vsddashboardapplication.repository.PortRepository
import pt.isel.vsddashboardapplication.model.NSPort

class PortListViewModel : BaseListViewModel<NSPort>() {

    override suspend fun setLiveData() {
        this.liveData.addSource(repo.getAll(nsg)) { liveData.value = it }
    }

    override suspend fun updateLiveData() { repo.updateAll(nsg) }


    private lateinit var repo: PortRepository
    private lateinit var nsg : String

    fun init(portRepository: PortRepository, nsgId: String){
        this.nsg = nsgId
        this.repo = portRepository
        viewModelScope.launch { setLiveData() }
    }

}
