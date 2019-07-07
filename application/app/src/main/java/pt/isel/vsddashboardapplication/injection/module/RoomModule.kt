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

}