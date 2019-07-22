package pt.isel.vsddashboardapplication.repository.implementation

import android.util.Log
import androidx.lifecycle.LiveData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import pt.isel.vsddashboardapplication.model.statistics.DpiProbestats
import pt.isel.vsddashboardapplication.repository.DpiProbestatsRepository
import pt.isel.vsddashboardapplication.repository.dao.DpiProbestatsDao
import pt.isel.vsddashboardapplication.repository.services.ElasticSearchRetrofitSingleton
import pt.isel.vsddashboardapplication.utils.ElasticSearchQueryBuilder
import javax.inject.Inject

class DpiProbestatsRepositoryImpl @Inject constructor(private val dao: DpiProbestatsDao)
    : DpiProbestatsRepository {
    companion object{ private const val TAG = "REPO/ENTERPRISE" }

    override fun getInbound(
        port: String,
        nsg: String,
        apm: String?,
        start: Long?,
        end: Long?
    ): LiveData<List<DpiProbestats>> {
        Log.d(TAG, "Getting values from $start to $end, for NSG - $nsg port $port")
        val value =  apm?.let { dao.loadInbound(nsg, port, apm, start?:0, end?:System.currentTimeMillis()) }
            ?: dao.loadInbound(nsg, port, start?:0, end?:System.currentTimeMillis())

        if(value.value == null)
            CoroutineScope(Dispatchers.IO).launch {
                updateInbound(port, nsg, apm, start, end, null)
            }

        return value
    }

    override fun getOutbound(
        port: String,
        nsg: String,
        apm: String?,
        start: Long?,
        end: Long?
    ): LiveData<List<DpiProbestats>> {
        val value =  apm?.let { dao.loadOutbound(nsg, port, apm, start?:0, end?:System.currentTimeMillis()) }
            ?: dao.loadOutbound(nsg, port, start?:0, end?:System.currentTimeMillis())
        if(value.value == null)
            CoroutineScope(Dispatchers.IO).launch {
                updateOutbound(port, nsg, apm, start, end, null)
            }

        return value
    }

    /**
     * Updates inbound
     */
    override suspend fun updateInbound(
        port: String,
        nsg: String,
        apm: String?,
        start: Long?,
        end: Long?,
        onFinished: ((Unit) -> Unit)?
    ) {
        withContext(Dispatchers.IO) {
            val builder = ElasticSearchQueryBuilder()
                .addSimpleQuery("DestinationNSG", nsg)
                .addSimpleQuery("DstUplink", port)
                .addRange("timestamp", start, end)

            apm?.run { builder.addSimpleQuery("APMGroup", apm) }
               ?: builder.addNullQuery("APMGroup")

            val query = builder.build()
            val sort = getSort()
            update(query, sort)

        }
    }

    override suspend fun updateOutbound(
        port: String,
        nsg: String,
        apm: String?,
        start: Long?,
        end: Long?,
        onFinished: ((Unit) -> Unit)?
    ) {
        withContext(Dispatchers.IO) {
            val builder = ElasticSearchQueryBuilder()
                .addSimpleQuery("SourceNSG", nsg)
                .addSimpleQuery("SrcUplink", port)
                .addRange("timestamp", start, end)

            apm?.run { builder.addSimpleQuery("APMGroup", apm) }
                ?: builder.addNullQuery("APMGroup")

            val query = builder.build()
            val sort = getSort()
            update(query, sort)
        }
    }

    private  suspend fun update(query: String, sort: String)  {
        val service = ElasticSearchRetrofitSingleton.dpiProbestats()
        val deferred = service?.getDpiProbestatsWithQuery( query = query, sort = sort )
        withContext(Dispatchers.IO) {
            val result = deferred?.await()

            val hits = result?.hits?.hits?.map { it.source }

            if (hits != null)
                for(hit in hits)
                    hit?.let { dao.save(it) }


            return@withContext
        }
    }

    private fun getSort() = "timestamp:desc"

}
