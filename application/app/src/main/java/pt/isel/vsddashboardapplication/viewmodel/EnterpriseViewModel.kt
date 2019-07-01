package pt.isel.vsddashboardapplication.viewmodel

import pt.isel.vsddashboardapplication.model.Enterprise
import pt.isel.vsddashboardapplication.repository.EnterpriseRepository

class EnterpriseViewModel : BaseListViewModel<Enterprise>() {

    private lateinit var repository: EnterpriseRepository
    private lateinit var userId : String

    override suspend fun setLiveData() {
        val value = repository.getAll(userId)
        liveData.addSource(value){ liveData.value = it }
    }

    override suspend fun updateLiveData() {
        repository.getAll(userId)
    }

    fun init(repository: EnterpriseRepository, id: String){
        this.repository = repository
        this.userId = id
    }

}