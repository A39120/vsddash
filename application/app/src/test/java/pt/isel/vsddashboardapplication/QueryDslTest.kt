package pt.isel.vsddashboardapplication

import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Test
import pt.isel.vsddashboardapplication.repository.services.ElasticSearchRetrofitSingleton

class QueryDslTest {

    /**
     * Test for ES, get all the hits for Nuage flow
     */
    @Test
    fun getProbestatWithQueryTest() {
        val uri = TestObject.esApi
        ElasticSearchRetrofitSingleton.set(uri)
        val service = ElasticSearchRetrofitSingleton.dpiProbestats()

        Assert.assertNotNull(service)
        val max = System.currentTimeMillis() - 3600 * 1000
        val min = System.currentTimeMillis() - 2 * (3600 * 1000)

        runBlocking {
            val probeJob = service?.getDpiProbestatsWithQuery(sort = "timestamp:desc", query = arrayOf("timestamp:[$min TO $max]"))
            Assert.assertNotNull(probeJob)
            val probe = probeJob!!.await()
            for(current in probe.hits.hits){
                val time = current.source?.timestamp
                Assert.assertNotNull(time)
                Assert.assertTrue(time!! > min && time < max)
            }
        }
    }

}