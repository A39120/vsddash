package pt.isel.vsddashboardapplication.injection.module

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import pt.isel.vsddashboardapplication.viewmodel.*
import pt.isel.vsddashboardapplication.viewmodel.authentication.ApiSettingsViewModel
import pt.isel.vsddashboardapplication.viewmodel.authentication.LoginViewModel
import pt.isel.vsddashboardapplication.viewmodel.factory.ViewModelFactory
import pt.isel.vsddashboardapplication.viewmodel.factory.ViewModelKey

@Module
abstract class ViewModelModule {

    @Binds
    internal abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(PortListViewModel::class)
    internal abstract fun portListViewModel(viewModel: PortListViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(NSGViewModel::class)
    internal abstract fun nsgViewModel(viewModel: NSGViewModel): ViewModel


    @Binds
    @IntoMap
    @ViewModelKey(AllNSGatewayViewModel::class)
    internal abstract fun allNsgViewModel(viewModel: AllNSGatewayViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(AlarmViewModel::class)
    internal abstract fun alarmViewModel(viewModel: AlarmViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(EnterpriseViewModel::class)
    internal abstract fun enterpriseViewModel(viewModel: EnterpriseViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(ApiSettingsViewModel::class)
    internal abstract fun apiSettingsViewModel(viewModel: ApiSettingsViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(LoginViewModel::class)
    internal abstract fun loginViewModel(viewModel: LoginViewModel): ViewModel

}