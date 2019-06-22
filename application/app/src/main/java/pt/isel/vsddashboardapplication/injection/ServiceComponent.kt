package pt.isel.vsddashboardapplication.injection

import androidx.fragment.app.Fragment
import dagger.Component
import dagger.android.AndroidInjector
import pt.isel.vsddashboardapplication.injection.module.RepositoryModule

@Component(
    dependencies = [AppComponent::class],
    modules = [RepositoryModule::class])
interface ServiceComponent : AndroidInjector<Fragment> {
    override fun inject(fragment: Fragment)
}