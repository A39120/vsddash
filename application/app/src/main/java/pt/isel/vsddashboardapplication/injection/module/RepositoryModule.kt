package pt.isel.vsddashboardapplication.injection.module

import dagger.Binds
import dagger.Module
import dagger.Provides
import pt.isel.vsddashboardapplication.VsdApplication
import pt.isel.vsddashboardapplication.repository.*
import pt.isel.vsddashboardapplication.repository.dao.EnterpriseDao
import pt.isel.vsddashboardapplication.repository.dao.NSAlarmDao
import pt.isel.vsddashboardapplication.repository.dao.NSGatewayDao
import pt.isel.vsddashboardapplication.repository.dao.NSPortDao
import pt.isel.vsddashboardapplication.repository.implementation.*
import pt.isel.vsddashboardapplication.utils.sharedPreferences
import javax.inject.Singleton

@Module
class RepositoryModule {

    @Provides
    @Singleton
    fun providesNsgRepository(dao : NSGatewayDao) : NSGatewayRepository =
        NSGatewayRepositoryImpl(dao)

    @Provides
    @Singleton
    fun providesAlarmRepository(dao: NSAlarmDao) : AlarmRepository =
        AlarmRepositoryImpl(dao)

    @Provides
    @Singleton
    fun providesPortRepository(dao: NSPortDao) : PortRepository =
        NSPortRepositoryImpl(dao)

    @Provides
    @Singleton
    fun providesEnterpriseRepository(dao: EnterpriseDao) : EnterpriseRepository =
        EnterpriseRepositoryImpl(dao)

    @Provides
    @Singleton
    fun providesApiSettingsRepository(application: VsdApplication) : ApiSettingsRepository =
        ApiSettingsRepositoryImpl(application.sharedPreferences())

    @Provides
    @Singleton
    fun providesLoginRepository(application: VsdApplication) : LoginRepository =
        LoginRepositoryImpl(application.sharedPreferences())

}
