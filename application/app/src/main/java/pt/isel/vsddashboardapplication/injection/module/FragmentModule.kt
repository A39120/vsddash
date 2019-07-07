package pt.isel.vsddashboardapplication.injection.module

import dagger.Module
import dagger.android.ContributesAndroidInjector
import pt.isel.vsddashboardapplication.activities.fragment.list.AlarmFragment
import pt.isel.vsddashboardapplication.activities.fragment.list.EnterpriseListFragment
import pt.isel.vsddashboardapplication.activities.fragment.list.NSGatewayListFragment
import pt.isel.vsddashboardapplication.activities.fragment.list.PortListFragment
import pt.isel.vsddashboardapplication.activities.fragment.regular.ApiSettingsFragment
import pt.isel.vsddashboardapplication.activities.fragment.regular.LoginFragment
import pt.isel.vsddashboardapplication.activities.fragment.regular.NSGatewayFragment

@Module
abstract class FragmentModule {

    @ContributesAndroidInjector
    abstract fun contributeNsgFragment() : NSGatewayFragment

    @ContributesAndroidInjector
    abstract fun contributeApiSettingsFragment() : ApiSettingsFragment

    @ContributesAndroidInjector
    abstract fun contributeLoginFragment() : LoginFragment

    @ContributesAndroidInjector
    abstract fun contributeAlarmFragment() : AlarmFragment

    @ContributesAndroidInjector
    abstract fun contributeEnterpriseListFragment() : EnterpriseListFragment

    @ContributesAndroidInjector
    abstract fun contributeNSGatewayListFragment() : NSGatewayListFragment

    @ContributesAndroidInjector
    abstract fun contributePortListFragment() : PortListFragment
}