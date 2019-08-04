package pt.isel.vsddashboardapplication.injection.module

import dagger.Module
import dagger.android.ContributesAndroidInjector
import pt.isel.vsddashboardapplication.activities.fragment.graph.PortAvgJitterGraphFragment
import pt.isel.vsddashboardapplication.activities.fragment.list.*
import pt.isel.vsddashboardapplication.activities.fragment.parent.VspParentFragment
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

    @ContributesAndroidInjector
    abstract fun contributeVspParentFragment() : VspParentFragment

    @ContributesAndroidInjector
    abstract fun contributeVspFragment() : VspFragment

    @ContributesAndroidInjector
    abstract fun contributeVscListFragment() : VscListFragment

}