package pt.isel.vsddashboardapplication

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Test
import pt.isel.vsddashboardapplication.communication.provider.HttpClientBuilderProvider
import pt.isel.vsddashboardapplication.repository.services.vsd.AuthenticationService
import pt.isel.vsddashboardapplication.model.Session
import pt.isel.vsddashboardapplication.repository.services.vsd.EventServices
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory


class SessionTest {

    @Test
    fun getSession(){

        val retrofit = Retrofit.Builder()
            .client(HttpClientBuilderProvider.getClient().build())
            .baseUrl(TestObject.api)
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .addConverterFactory(MoshiConverterFactory.create())
            .build()

        val authService = retrofit.create(AuthenticationService::class.java)
        var session : List<Session>? = null

        runBlocking {
            val res =  authService.authenticate(
                authorization = TestObject.getAuth(),
                organization = TestObject.organization
            )
            session = res.await()
        }

        GlobalScope.launch{ delay(10000) }

        val eventServ = retrofit.create(EventServices::class.java)
        val event = eventServ.getEvent(uuid = "378c0770-b7a1-47e8-9a62-c8d1bcebd5b1")
        runBlocking {
            val res = event.await()
        }
        Assert.assertNotNull(session?.get(0))
        Assert.assertNotNull(session?.get(0)?.enterpriseName)

    }
}



