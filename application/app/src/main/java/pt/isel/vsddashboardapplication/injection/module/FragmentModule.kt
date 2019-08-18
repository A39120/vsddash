package pt.isel.vsddashboardapplication.injection.module

import dagger.Module
import dagger.android.ContributesAndroidInjector
import pt.isel.vsddashboardapplication.activities.fragment.graph.*
import pt.isel.vsddashboardapplication.activities.fragment.list.*
import pt.isel.vsddashboardapplication.activities.fragment.parent.*
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
    abstract fun contributePortListFragment() : NSPortListFragment

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

    @ContributesAndroidInjector
    abstract fun contributeVrsListFragment() : VrsListFragment

    @ContributesAndroidInjector
    abstract fun contributeVcsParentFragment() : VscParentFragment

    @ContributesAndroidInjector
    abstract fun contributeVcsFragment() : VscFragment

    @ContributesAndroidInjector
    abstract fun contributeVrsFragment() : VrsFragment

    @ContributesAndroidInjector
    abstract fun contributeVrsParentFragment() : VrsParentFragment

    @ContributesAndroidInjector
    abstract fun contributesVportListFragment() : VPortListFragment

    @ContributesAndroidInjector
    abstract fun contributesVportParentFragment() : VportParentFragment

    @ContributesAndroidInjector
    abstract fun contributesVportFragment() : VportFragment

    @ContributesAndroidInjector
    abstract fun contributesSysmonParentFragment() : ParentSysmonFragment

    @ContributesAndroidInjector
    abstract fun contributesSysmonFragment() : CpuMonitorGraphFragment

    @ContributesAndroidInjector
    abstract fun contributesPortStatisticsParentFragment() : PortStatisticsParentFragment

    @ContributesAndroidInjector
    abstract fun contributesMemoryMonitorGraphFragment() : MemoryMonitorGraphFragment

    @ContributesAndroidInjector
    abstract fun contributesPktLossGraphFragment() : PortPacketLossGraphFragment

    @ContributesAndroidInjector
    abstract fun contributesAvgDekayGraphFragment() : PortAvgDelayGraphFragment
}