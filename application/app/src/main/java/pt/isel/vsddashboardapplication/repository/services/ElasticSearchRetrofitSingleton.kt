package pt.isel.vsddashboardapplication.repository.services

import pt.isel.vsddashboardapplication.communication.provider.HttpClientBuilderProvider
import pt.isel.vsddashboardapplication.communication.provider.RetrofitBuilderProvider
import pt.isel.vsddashboardapplication.repository.services.es.DpiProbestatsServices
import retrofit2.Retrofit

object ElasticSearchRetrofitSingleton {

    private var retrofit: Retrofit? = null
    private var dpiProbestatsServices : DpiProbestatsServices? = null

    fun dpiProbestats() : DpiProbestatsServices? {
        if(dpiProbestatsServices == null)
            dpiProbestatsServices = retrofit?.create(DpiProbestatsServices::class.java)

        return dpiProbestatsServices
    }

    fun set(api: String){
        val httpClient = HttpClientBuilderProvider
            .getClient()
            .build()

        retrofit = RetrofitBuilderProvider
            .getBuilder()
            .baseUrl(api)
            .client(httpClient)
            .build()
    }
}