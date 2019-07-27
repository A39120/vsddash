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
    @ViewModelKey(NSGAlarmViewModel::class)
    internal abstract fun alarmViewModel(viewModelNSG: NSGAlarmViewModel): ViewModel

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

    @Binds
    @IntoMap
    @ViewModelKey(NSPortViewModel::class)
    internal abstract fun nsgPortViewModel(viewModel: NSPortViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(NSGInfoViewModel::class)
    internal abstract fun nsgInfoViewModel(viewModel: NSGInfoViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(ProbestatsViewModel::class)
    internal abstract fun probestatsViewModel(viewModel: ProbestatsViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(VscListViewModel::class)
    internal abstract fun vscListViewModel(viewModel: VscListViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(VrsListViewModel::class)
    internal abstract fun vrsListViewModel(viewModel: VrsListViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(VspViewModel::class)
    internal abstract fun vspViewModel(viewModel: VspViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(VrsViewModel::class)
    internal abstract fun vrsViewModel(viewModel: VrsViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(VscViewModel::class)
    internal abstract fun vscViewModel(viewModel: VscViewModel): ViewModel
}
