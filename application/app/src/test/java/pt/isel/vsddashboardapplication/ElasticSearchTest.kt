package pt.isel.vsddashboardapplication

import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Test
import pt.isel.vsddashboardapplication.model.statistics.DpiProbestats
import pt.isel.vsddashboardapplication.model.statistics.base.Search
import pt.isel.vsddashboardapplication.repository.services.ElasticSearchRetrofitSingleton

class ElasticSearchTest {

    /**
     * Test for ES, get all the hits for nuage flow
     */
    @Test
    fun getFlowTest() {
        val uri = TestObject.esApi
        ElasticSearchRetrofitSingleton.set(uri)

        val service = ElasticSearchRetrofitSingleton.dpiProbestats()
        Assert.assertNotNull(service)

        val flow = service?.getDpiProbestats()
        Assert.assertNotNull(flow)

        var res : Search<DpiProbestats>? = null
        runBlocking { res = flow?.await() }

        Assert.assertNotNull(res)
        print(res.toString())
    }

}
