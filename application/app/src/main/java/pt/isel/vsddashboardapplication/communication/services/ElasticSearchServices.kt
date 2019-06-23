package pt.isel.vsddashboardapplication.communication.services

import pt.isel.vsddashboardapplication.communication.provider.HttpClientBuilderProvider
import pt.isel.vsddashboardapplication.communication.provider.RetrofitBuilderProvider
import retrofit2.Retrofit

/**
 * Singleton that contains the retrofit class necessary to build
 * the services that interact with the Elastic Search database
 *
 * @param uri: the elastic search address
 */
class ElasticSearchServices private constructor(private val uri: String){
    companion object {

        private var instance: ElasticSearchServices? = null
        fun getInstance(uri: String? = null) : ElasticSearchServices? {
            if(uri != null)
                instance = ElasticSearchServices(uri)
            return instance
        }

    }

    private var esRetrofit: Retrofit? = null


    /**
     * Builds a service for the Elastic Search API
     * @param klass the service we will construct
     * @return the service we built or null if there were problems building
     */
    fun <T> getService(klass: Class<T>): T? {
        if(esRetrofit == null)
            buildEsRetrofit()

        return esRetrofit?.create(klass)
    }

    /**
     * Builds the single class esRetrofit if it's null
     */
    private fun buildEsRetrofit() {
        val httpClient = HttpClientBuilderProvider
            .getClient()
            .build()

        val retrofit = RetrofitBuilderProvider
            .getBuilder()
            .baseUrl(uri)
            .client(httpClient)
            .build()

        this.esRetrofit = retrofit
    }

}