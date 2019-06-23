package pt.isel.vsddashboardapplication

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Test
import pt.isel.vsddashboardapplication.communication.services.vsd.AuthenticationService
import pt.isel.vsddashboardapplication.model.Session
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory


class SessionTest {

    @Test
    fun getSession(){

        val retrofit = Retrofit.Builder()
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
        Assert.assertNotNull(session?.get(0))
        Assert.assertNotNull(session?.get(0)?.enterpriseName)

    }
}



