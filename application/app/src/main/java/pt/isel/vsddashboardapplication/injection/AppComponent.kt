package pt.isel.vsddashboardapplication.injection

import android.app.Application
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.AndroidInjector
import pt.isel.vsddashboardapplication.VsdApplication
import pt.isel.vsddashboardapplication.injection.module.ApplicationModule
import pt.isel.vsddashboardapplication.injection.module.RepositoryModule
import pt.isel.vsddashboardapplication.injection.module.RoomModule
import javax.inject.Singleton

/**
 * Provides the Modules to the entire scope of the application
 */
@Singleton
@Component(modules = [
    AndroidInjectionModule::class,
    ApplicationModule::class,
    RoomModule::class,
    RepositoryModule::class
])
interface AppComponent  : AndroidInjector<VsdApplication>{

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun application(application: Application) : Builder

        fun build() : AppComponent

    }

    override fun inject(instance: VsdApplication)
}