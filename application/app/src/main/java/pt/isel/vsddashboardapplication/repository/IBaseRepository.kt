package pt.isel.vsddashboardapplication.repository

import androidx.lifecycle.LiveData

/**
 * The base interface of a repository
 * @param T: the class of the resource
 */
interface IBaseRepository<T> {

    /**
     * Gets a resource
     * @param id: the id of the resource
     * @return LiveData containing the resource
     */
    suspend fun get(id: String) : LiveData<T>

    /**
     * Gets all the resources of the same class that are children of a defined parent
     * @param parentId: the id of the parent, used to get all it's children
     * @return LiveData with a list of resources
     */
    suspend fun getAll(parentId: String) : LiveData<List<T>>

    /**
     * Updates the resource
     * @param id: the id of the specific resource
     */
    suspend fun update(id: String, onFinish: (() -> Unit)? = null)

    /**
     * Updates all the resources that are children of a defined parent
     * @param parentId: the id of the parent of the resources
     */
    suspend fun updateAll(parentId: String, onFinish: (() -> Unit)? = null)

}