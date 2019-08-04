package pt.isel.vsddashboardapplication.repository.base

import androidx.lifecycle.LiveData
import pt.isel.vsddashboardapplication.model.VSP

/**
 * The repository responsible for handling the VSP
 */
interface VspRepository {

    /**
     * Gets a resource
     * @return LiveData containing the VSP
     */
     fun get() : LiveData<List<VSP>?>

    /**
     * Updates the VSP
     * @param id: the id of the specific resource
     */
    suspend fun update(onFinish: (() -> Unit)? = null)

}