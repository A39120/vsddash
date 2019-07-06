package pt.isel.vsddashboardapplication.viewmodel

import pt.isel.vsddashboardapplication.model.statistics.DpiProbestats


class ProbestatsViewModel : BaseViewModel<List<DpiProbestats>>() {
    companion object{
        private const val TAG = "VM/PROBESTATS"
    }

    override suspend fun setLiveData() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override suspend fun updateLiveData() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}