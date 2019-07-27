package pt.isel.vsddashboardapplication.injection

import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.AndroidInjector
import pt.isel.vsddashboardapplication.VsdApplication
import pt.isel.vsddashboardapplication.injection.module.*
import javax.inject.Singleton

/**
 * Provides the Modules to the entire scope of the application
 */
@Singleton
@Component(modules = [
    AndroidInjectionModule::class,
    ApplicationModule::class,
    RoomModule::class,
    RepositoryModule::class,
    ActivityBuilder::class,
    FragmentModule::class,
    ViewModelModule::class
])
interface AppComponent  : AndroidInjector<VsdApplication>{

    override fun inject(instance: VsdApplication)

}