package pt.isel.vsddashboardapplication.injection.module

import dagger.Module
import dagger.Provides
import pt.isel.vsddashboardapplication.communication.BaseHttpClient

@Module
class NetworkModule  {

    @Provides
    fun providesHttp3ClientBuilder() = BaseHttpClient.getClient()
}