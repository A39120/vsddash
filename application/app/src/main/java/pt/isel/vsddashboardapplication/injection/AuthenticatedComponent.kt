package pt.isel.vsddashboardapplication.injection

import androidx.fragment.app.Fragment
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.AndroidInjector
import pt.isel.vsddashboardapplication.VsdApplication
import pt.isel.vsddashboardapplication.injection.module.*
import javax.inject.Singleton

/*
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
interface AuthenticatedComponent  : AndroidInjector<Fragment> {

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun applicationModule(application: VsdApplication) : Builder

        fun build() : AppComponent

    }

    override fun inject(instance: Fragment)
}
*/
