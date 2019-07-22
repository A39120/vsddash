package pt.isel.vsddashboardapplication.injection.module

import dagger.Module
import dagger.android.ContributesAndroidInjector
import pt.isel.vsddashboardapplication.activities.LoadingActivity
import pt.isel.vsddashboardapplication.activities.MainActivity
import pt.isel.vsddashboardapplication.activities.NSPortActivity
import pt.isel.vsddashboardapplication.activities.NsgActivity

@Module
abstract class ActivityBuilder {

    @ContributesAndroidInjector
    abstract fun contributeNsgActivity() : NsgActivity

    @ContributesAndroidInjector
    abstract fun contributeMainActivity() : MainActivity

    @ContributesAndroidInjector
    abstract fun contributeLoadingActivity() : LoadingActivity

    @ContributesAndroidInjector
    abstract fun contributeNSPortActivity() : NSPortActivity
}
