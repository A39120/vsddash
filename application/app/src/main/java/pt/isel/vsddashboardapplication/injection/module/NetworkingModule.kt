package pt.isel.vsddashboardapplication.injection.module

import dagger.Module
import dagger.Provides
import okhttp3.Cache
import okhttp3.OkHttpClient
import okhttp3.internal.cache.CacheInterceptor
import pt.isel.vsddashboardapplication.communication.http.AuthenticationService
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
class NetworkingModule {

    @Singleton
    @Provides
    fun provideOkHttpClient(cache: Cache) : OkHttpClient {
        return OkHttpClient.Builder()
            .cache(cache)
            .build()
    }

    @Singleton
    @Provides
    fun provideApiService(retrofit: Retrofit) : AuthenticationService {
        return retrofit.create(AuthenticationService::class.java)
    }

}