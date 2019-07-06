package pt.isel.vsddashboardapplication

import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Test
import pt.isel.vsddashboardapplication.repository.services.ElasticSearchServices
import pt.isel.vsddashboardapplication.repository.services.es.ElasticSearchService
import pt.isel.vsddashboardapplication.model.statistics.Flow
import pt.isel.vsddashboardapplication.model.statistics.base.Search

class ElasticSearchTest {

    /**
     * Test for ES, get all the hits for nuage flow
     */
    @Test
    fun getFlowTest() {
        val uri = TestObject.esApi
        val client = ElasticSearchServices.getInstance(uri)
        Assert.assertNotNull(client)

        val service = client?.getService(ElasticSearchService::class.java)
        Assert.assertNotNull(service)

        val flow = service?.getFlow()
        Assert.assertNotNull(flow)

        var res : Search<Flow>? = null
        runBlocking { res = flow?.await() }

        Assert.assertNotNull(res)
        print(res.toString())
    }
}
