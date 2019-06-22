package pt.isel.vsddashboardapplication.injection.module

import dagger.Module
import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import javax.inject.Singleton
import dagger.Provides
import pt.isel.vsddashboardapplication.VsdApplication


@Module
class ApplicationModule{
    companion object {
        private const val BASE_SP = "vsdsharedpreferences"
    }

    private lateinit var application: VsdApplication

    fun AppModule(application: VsdApplication) {
        this.application = application
    }

    @Provides
    @Singleton
    fun providesApplication(): VsdApplication =
        application

    @Provides
    @Singleton
    fun providesSharedDependencies() : SharedPreferences
            = this.application.getSharedPreferences(BASE_SP, Context.MODE_PRIVATE)

}