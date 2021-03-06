package pt.isel.vsddashboardapplication.injection.module

import android.content.Context
import dagger.Module
import dagger.Provides
import pt.isel.vsddashboardapplication.repository.database.VsdDatabase
import javax.inject.Singleton

@Module
class RoomModule {

    @Singleton
    @Provides
    fun providesDatabase(context: Context) = VsdDatabase.getInstance(context)

    @Singleton
    @Provides
    fun providesNSGatewayDao(database: VsdDatabase) = database.nsgDao()

    @Singleton
    @Provides
    fun providesAlarmDao(database: VsdDatabase) = database.nsAlarmDao()

    @Singleton
    @Provides
    fun providesPortDao(database: VsdDatabase) = database.nsportDao()

    @Singleton
    @Provides
    fun providesEnterpriseDao(database: VsdDatabase) = database.enterpriseDao()

    @Singleton
    @Provides
    fun providesProbestatsDao(database: VsdDatabase) = database.dpiProbestatsDao()

    @Singleton
    @Provides
    fun providesApmDao(database: VsdDatabase) = database.apmDao()

    @Singleton
    @Provides
    fun providesVspDao(database: VsdDatabase) = database.vspDao()

    @Singleton
    @Provides
    fun providesVscDao(database: VsdDatabase) = database.vscDao()

    @Singleton
    @Provides
    fun providesVrsDao(database: VsdDatabase) = database.vrsDao()

    @Singleton
    @Provides
    fun providesNsgInfoDao(database: VsdDatabase) = database.nsgInfoDao()

    @Singleton
    @Provides
    fun providesVportDao(database: VsdDatabase) = database.vportDao()

    @Singleton
    @Provides
    fun providesPerformanceMonitorDao(database: VsdDatabase) = database.performanceMonitorDao()

    @Singleton
    @Provides
    fun providesSysmonDao(database: VsdDatabase) = database.sysmonDao()

    @Singleton
    @Provides
    fun providesEventsDao(database: VsdDatabase) = database.eventsDao()

    @Singleton
    @Provides
    fun providesHealthDao(database: VsdDatabase) = database.healthDao()
}