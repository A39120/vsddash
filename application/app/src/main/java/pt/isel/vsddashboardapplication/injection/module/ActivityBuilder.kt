package pt.isel.vsddashboardapplication.injection.module

import dagger.Module
import dagger.android.ContributesAndroidInjector
import pt.isel.vsddashboardapplication.activities.LoadingActivity
import pt.isel.vsddashboardapplication.activities.MainActivity
import pt.isel.vsddashboardapplication.activities.NSPortPagerFragment
import pt.isel.vsddashboardapplication.activities.NsgPagerFragment

@Module
abstract class ActivityBuilder {

    @ContributesAndroidInjector
    abstract fun contributeNsgActivity() : NsgPagerFragment

    @ContributesAndroidInjector
    abstract fun contributeMainActivity() : MainActivity

    @ContributesAndroidInjector
    abstract fun contributeLoadingActivity() : LoadingActivity

    @ContributesAndroidInjector
    abstract fun contributeNSPortActivity() : NSPortPagerFragment
}
