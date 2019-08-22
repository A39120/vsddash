package pt.isel.vsddashboardapplication.repository.base.implementation

import androidx.lifecycle.LiveData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import pt.isel.vsddashboardapplication.model.statistics.Sysmon
import pt.isel.vsddashboardapplication.repository.base.SysmonRepository
import pt.isel.vsddashboardapplication.repository.dao.SysmonDao
import pt.isel.vsddashboardapplication.repository.services.ElasticSearchRetrofitSingleton
import pt.isel.vsddashboardapplication.utils.ElasticSearchQueryBuilder
import javax.inject.Inject

class SysmonRepositoryImpl @Inject constructor(
    private val sysmonDao: SysmonDao
): SysmonRepository {

    override suspend fun update(id: String, from: Long, to: Long) {
        val query = ElasticSearchQueryBuilder()
            .addSimpleQuery("system_id", id)
            .addRange("timestamp", from, to)
            .build()

        val size = ElasticSearchQueryBuilder.calculateSize(from, to)
        updatePage(query, size)
    }

    override fun get(id: String, from: Long, to: Long): LiveData<List<Sysmon>> =
        sysmonDao.load(id)//, from, to)


    /**
     * Updates the page of Sysmon
     * @param query the details of the search sent to the service
     * @param size the maximum size of a result
     * @param page the current page
     */
    private suspend fun updatePage(query: String, size: Int) {
        val timestamp = "timestamp:asc"
        var page = 0
        withContext(Dispatchers.IO) {
            var stop = false

            do {
                val deferred = ElasticSearchRetrofitSingleton
                    .sysmon()
                    ?.getSysmonWithQuery(query,timestamp, page, size)

                deferred?.await().let {
                    if(size > it?.hits?.hits?.size?:0)
                        stop = true

                    val results = it?.hits?.hits?.mapNotNull { res -> res.source }
                        ?: listOf()
                    sysmonDao.saveAll(results)
                    page += size
                }
            } while(!stop)

        }
    }

}