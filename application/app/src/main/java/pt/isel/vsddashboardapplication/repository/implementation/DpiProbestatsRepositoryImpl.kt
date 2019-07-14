package pt.isel.vsddashboardapplication.repository.implementation

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import pt.isel.vsddashboardapplication.model.statistics.DpiProbestats
import pt.isel.vsddashboardapplication.repository.DpiProbestatsRepository
import pt.isel.vsddashboardapplication.repository.dao.DpiProbestatsDao
import pt.isel.vsddashboardapplication.repository.services.ElasticSearchRetrofitSingleton
import javax.inject.Inject

class DpiProbestatsRepositoryImpl @Inject constructor(private val dao: DpiProbestatsDao)
    : DpiProbestatsRepository {
    companion object{ private const val TAG = "REPO/ENTERPRISE" }

    override suspend fun getInbound(port: String, nsg: String, start: Long, end: Long): LiveData<List<DpiProbestats>> {
        Log.d(TAG, "Getting values from $start to $end, for NSG - $nsg port $port")

        val query = arrayOf("DstUplink:$port", "DestinationNSG:$nsg", "timestamp:[$start to $end]")
        val sort = "timestamp:desc"

        ElasticSearchRetrofitSingleton
            .dpiProbestats()
            ?.getDpiProbestatsWithQuery(query = query, sort = sort)

        return liveData {  }

    }

    override suspend fun getOutbound(port: String, nsg: String, start: Long, end: Long): LiveData<List<DpiProbestats>> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override suspend fun updateInbound(start: Long, end: Long, onFinished: ((Unit) -> Unit)?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override suspend fun updateOutbound(start: Long, end: Long, onFinished: ((Unit) -> Unit)?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    private fun get(port: String, nsg: String, start: Long, end: Long){

    }


}
