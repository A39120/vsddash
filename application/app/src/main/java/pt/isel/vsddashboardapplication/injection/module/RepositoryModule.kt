package pt.isel.vsddashboardapplication.injection.module

import dagger.Module
import dagger.Provides
import pt.isel.vsddashboardapplication.VsdApplication
import pt.isel.vsddashboardapplication.repository.base.*
import pt.isel.vsddashboardapplication.repository.dao.*
import pt.isel.vsddashboardapplication.repository.base.implementation.*
import pt.isel.vsddashboardapplication.utils.sharedPreferences
import javax.inject.Singleton

@Module
class RepositoryModule {

    @Provides
    @Singleton
    fun providesNsgRepository(dao : NSGatewayDao, alarmDao: AlarmDao, nsgInfoDao: NSGInfoDao) : NSGatewayRepository =
        NSGatewayRepositoryImpl(dao, alarmDao, nsgInfoDao)

    @Provides
    @Singleton
    fun providesAlarmRepository(dao: AlarmDao) : AlarmRepository =
        AlarmRepositoryImpl(dao)

    @Provides
    @Singleton
    fun providesPortRepository(dao: NSPortDao, alarmDao: AlarmDao) : NSPortRepository =
        NSPortRepositoryImpl(dao, alarmDao)

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

    @Provides
    @Singleton
    fun providesDpiProbestatsRepository(dao: DpiProbestatsDao) : DpiProbestatsRepository =
        DpiProbestatsRepositoryImpl(dao)

    @Provides
    @Singleton
    fun providesApmRepository(dao: ApmDao) : ApmRepository =
        ApmRepositoryImpl(dao)

    @Provides
    @Singleton
    fun providesNsgInfoRepository(dao: NSGInfoDao) : NSGinfoRepository =
        NSGInfoRepositoryImpl(dao)

    @Provides
    @Singleton
    fun providesVrsRepository(dao: VrsDao, alarmDao: AlarmDao, vportDao: VPortDao) : VrsRepository =
        VrsRepositoryImpl(dao, alarmDao, vportDao)

    @Provides
    @Singleton
    fun providesVscRepository(dao: VscDao, alarmDao: AlarmDao, vrsDao: VrsDao) : VscRepository =
        VscRepositoryImpl(dao, alarmDao, vrsDao)

    @Provides
    @Singleton
    fun providesVspRepository(dao: VspDao) : VspRepository =
        VspRepositoryImpl(dao)

    @Provides
    @Singleton
    fun providesVportRepository(dao: VPortDao, alarmDao: AlarmDao) : VPortRepository =
        VPortRepositoryImpl(dao, alarmDao)

    @Provides
    @Singleton
    fun providesPerformanceMonitorRepository(dao: PerformanceMonitorDao) : PerformanceMonitorRepository =
        PerformanceMonitorRepositoruImpl(dao)

    @Provides
    @Singleton
    fun providesSysmonRepository(dao: SysmonDao) : SysmonRepository =
        SysmonRepositoryImpl(dao)

    @Provides
    @Singleton
    fun providesHealthRepository(dao: HealthDao) : HealthRepository =
        HealthRepositoryImpl(dao)
}
