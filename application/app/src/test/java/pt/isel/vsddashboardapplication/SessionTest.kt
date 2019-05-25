package pt.isel.vsddashboardapplication

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Test
import pt.isel.vsddashboardapplication.communication.http.AuthenticationService
import pt.isel.vsddashboardapplication.communication.http.model.Session
import pt.isel.vsddashboardapplication.injection.module.VsdClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory


class SessionTest {

    @Test
    fun getSession(){

        val retrofit = Retrofit.Builder()
            .baseUrl(TestObject.api)
            .client(VsdClient.getClient())
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .addConverterFactory(MoshiConverterFactory.create())
            .build()

        val authService = retrofit.create(AuthenticationService::class.java)
        var session : Session? = null

        runBlocking {
            val res =  authService.authenticate(
                TestObject.organization,
                TestObject.getAuth()
            )
            session = res
        }

        GlobalScope.launch{ delay(10000) }
        Assert.assertNotNull(session)
        Assert.assertNotNull(session!!.apiKey)

    }
}