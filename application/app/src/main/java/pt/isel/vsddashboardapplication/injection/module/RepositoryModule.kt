package pt.isel.vsddashboardapplication.injection.module

import dagger.Binds
import dagger.Module
import pt.isel.vsddashboardapplication.injection.scope.ServiceScope
import pt.isel.vsddashboardapplication.repository.AlarmRepository
import pt.isel.vsddashboardapplication.repository.NSGatewayRepository
import pt.isel.vsddashboardapplication.repository.PortRepository
import pt.isel.vsddashboardapplication.repository.implementation.AlarmRepositoryImpl
import pt.isel.vsddashboardapplication.repository.implementation.NSGatewayRepositoryImpl
import pt.isel.vsddashboardapplication.repository.implementation.NSPortRepositoryImpl

@Module
abstract class RepositoryModule {

    @Binds
    @ServiceScope
    abstract fun providesNsgRepository(impl : NSGatewayRepositoryImpl) : NSGatewayRepository

    @Binds
    @ServiceScope
    abstract fun providesAlarmRepository(impl: AlarmRepositoryImpl) : AlarmRepository

    @Binds
    @ServiceScope
    abstract fun providesPortRepository(impl: NSPortRepositoryImpl) : PortRepository

}
