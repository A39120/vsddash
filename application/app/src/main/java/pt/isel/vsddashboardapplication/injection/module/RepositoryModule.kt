package pt.isel.vsddashboardapplication.injection.module

import dagger.Binds
import dagger.Module
import pt.isel.vsddashboardapplication.injection.scope.ServiceScope
import pt.isel.vsddashboardapplication.repository.AlarmRepository
import pt.isel.vsddashboardapplication.repository.NSGatewayRepository
import pt.isel.vsddashboardapplication.repository.PortRepository
import pt.isel.vsddashboardapplication.repository.implementation.AlarmRepoImpl
import pt.isel.vsddashboardapplication.repository.implementation.NSGatewayRepoImpl
import pt.isel.vsddashboardapplication.repository.implementation.NSPortRepoImpl

@Module
abstract class RepositoryModule {

    @Binds
    @ServiceScope
    abstract fun providesNsgRepository(impl : NSGatewayRepoImpl) : NSGatewayRepository

    @Binds
    @ServiceScope
    abstract fun providesAlarmRepository(impl: AlarmRepoImpl) : AlarmRepository

    @Binds
    @ServiceScope
    abstract fun providesPortRepository(impl: NSPortRepoImpl) : PortRepository

}
