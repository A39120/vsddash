package pt.isel.vsddashboardapplication.injection.module

import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import javax.inject.Singleton

@Module
class NetworkingModule {

    @Provides
    @Singleton
    fun providesHttpClient() = OkHttpClient
        .Builder()
        .build()

}