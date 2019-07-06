package pt.isel.vsddashboardapplication.viewmodel

import android.util.Log
import pt.isel.vsddashboardapplication.model.Enterprise
import pt.isel.vsddashboardapplication.repository.EnterpriseRepository
import javax.inject.Inject

class EnterpriseViewModel : BaseListViewModel<Enterprise>() {
    companion object {
        private const val TAG = "VM/ENTERPRISES"
    }

    @Inject var repository: EnterpriseRepository? = null

    private lateinit var userId : String

    override suspend fun setLiveData() {
        Log.d(TAG, "Setting livedata with all enterprises for user $userId (repository = ${repository?.hashCode() ?: 0}")
        repository?.let { enterpriseRepository ->
            val value = enterpriseRepository.getAll(userId)
            liveData.addSource(value){ liveData.value = it }
        }
    }

    override suspend fun updateLiveData() {
        Log.d(TAG, "Updating livedata with all enterprises for user $userId (repository = ${repository?.hashCode() ?: 0}")
        repository?.getAll(userId)
    }

    fun init(id: String){
        Log.d(TAG, "Setting User ID = $id")
        this.userId = id
    }

}