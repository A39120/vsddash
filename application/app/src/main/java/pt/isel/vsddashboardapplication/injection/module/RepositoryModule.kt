package pt.isel.vsddashboardapplication.injection.module

import dagger.Module
import dagger.Provides
import pt.isel.vsddashboardapplication.injection.scope.ServiceScope
import pt.isel.vsddashboardapplication.repository.NSGatewayRepository
import pt.isel.vsddashboardapplication.repository.implementation.NSGatewayRepoImpl

@Module
class RepositoryModule {

    @Provides
    @ServiceScope
    fun provideNsgRespository() : NSGatewayRepository = NSGatewayRepoImpl()



}