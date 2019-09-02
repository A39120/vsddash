package pt.isel.vsddashboardapplication.repository.dao

import androidx.lifecycle.LiveData

interface BaseDao<T> {

    fun save(arg: T)

    fun delete(vararg args : T)

    fun load(id: String) : LiveData<T?>

    fun delete(id: String)

    fun deleteAll()
}