package pt.isel.vsddashboardapplication.repository.base

import androidx.lifecycle.LiveData
import pt.isel.vsddashboardapplication.model.Health

interface HealthRepository {

    fun get() : LiveData<List<Health>?>

    suspend fun update(onSuccess: (() -> Unit)? = null)
}