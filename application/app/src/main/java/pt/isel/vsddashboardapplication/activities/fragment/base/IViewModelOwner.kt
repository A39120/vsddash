package pt.isel.vsddashboardapplication.activities.fragment.base

import androidx.lifecycle.ViewModel

interface IViewModelOwner<T : ViewModel> {

    /**
     * Assigns the View model
     */
    fun assignViewModel() : T

    /**
     * Initiates the view model
     */
    fun initViewModel()

}