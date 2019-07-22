package pt.isel.vsddashboardapplication.injection.module

import dagger.Module
import dagger.android.ContributesAndroidInjector
import pt.isel.vsddashboardapplication.activities.fragment.graph.PortAvgJitterGraphFragment
import pt.isel.vsddashboardapplication.activities.fragment.list.AlarmFragment
import pt.isel.vsddashboardapplication.activities.fragment.list.EnterpriseListFragment
import pt.isel.vsddashboardapplication.activities.fragment.list.NSGatewayListFragment
import pt.isel.vsddashboardapplication.activities.fragment.list.PortListFragment
import pt.isel.vsddashboardapplication.activities.fragment.regular.*

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

    @ContributesAndroidInjector
    abstract fun contributeNSPortFragment() : NSPortFragment

    @ContributesAndroidInjector
    abstract fun contributePortStatisticsFragment() : PortStatisticsFragment

    @ContributesAndroidInjector
    abstract fun contributePortAvgJitterGraphFragment() : PortAvgJitterGraphFragment
}