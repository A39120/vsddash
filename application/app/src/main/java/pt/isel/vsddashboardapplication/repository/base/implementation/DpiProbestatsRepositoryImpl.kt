package pt.isel.vsddashboardapplication.repository.base.implementation

import android.util.Log
import androidx.lifecycle.LiveData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import pt.isel.vsddashboardapplication.model.statistics.DpiProbestats
import pt.isel.vsddashboardapplication.repository.base.DpiProbestatsRepository
import pt.isel.vsddashboardapplication.repository.dao.DpiProbestatsDao
import pt.isel.vsddashboardapplication.repository.services.ElasticSearchRetrofitSingleton
import pt.isel.vsddashboardapplication.utils.ElasticSearchQueryBuilder
import java.lang.Exception
import javax.inject.Inject

class DpiProbestatsRepositoryImpl @Inject constructor(private val dao: DpiProbestatsDao)
    : DpiProbestatsRepository {
    companion object{
        private const val TAG = "REPO/DPI_PROBE"
        private const val SIZE = 200
    }

    override fun getInbound(
        port: String,
        nsg: String,
        apm: String?,
        start: Long?,
        end: Long?
    ): LiveData<List<DpiProbestats>?> {
        Log.d(TAG, "Getting values from $start to $end, for NSG - $nsg port $port")
        return apm?.let { dao.loadInbound(nsg, port, apm, start?:0, end?:System.currentTimeMillis()) }
            ?: dao.loadInbound(nsg, port, start?:0, end?:System.currentTimeMillis())
    }

    override fun getOutbound(
        port: String,
        nsg: String,
        apm: String?,
        start: Long?,
        end: Long?
    ): LiveData<List<DpiProbestats>?> {
        return apm?.let { dao.loadOutbound(nsg, port, apm, start?:0, end?:System.currentTimeMillis()) }
            ?: dao.loadOutbound(nsg, port, start?:0, end?:System.currentTimeMillis())
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

    /**
     * Updates with data from elastic search;
     * @param query: the  ES query;
     */
    private  suspend fun update(query: String, sort: String, size: Int = SIZE, offset: Int = 0)  {
        Log.d(TAG, "Updating DPI Probestats")
        val service = ElasticSearchRetrofitSingleton.dpiProbestats()
        withContext(Dispatchers.IO) {
            try {
                val deferred =
                    service?.getDpiProbestatsWithQuery(query = query, sort = sort, size = size, offset = offset)
                val result = deferred?.await()

                val hits = result?.hits?.hits?.map { it.source }

                if (hits != null)
                    for (hit in hits)
                        hit?.let { dao.save(it) }

                //Recursive search
                result?.run {
                    Log.d(TAG, "Getting update - $offset of ${this.hits.total ?: 0}")
                    if (this.hits.total ?: 0 > offset + size && offset + size < 10000)
                        update(query, sort, size, offset + size)
                }
            } catch (ex: Exception) {}

            return@withContext
        }
    }

    private fun getSort() = "timestamp:asc"

}
