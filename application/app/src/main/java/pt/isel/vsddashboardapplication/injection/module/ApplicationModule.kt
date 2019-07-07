package pt.isel.vsddashboardapplication.injection.module

import android.content.Context
import dagger.Module
import dagger.Provides
import javax.inject.Singleton
import android.content.SharedPreferences
import pt.isel.vsddashboardapplication.VsdApplication
import pt.isel.vsddashboardapplication.utils.sharedPreferences

@Module
class ApplicationModule(val application: VsdApplication) {


    @Provides
    fun providesApplication(): VsdApplication {
        return application
    }

    @Provides
    @Singleton
    fun providePreferences(application: VsdApplication): SharedPreferences {
        return application.sharedPreferences()
    }


    @Provides
    fun providesContext(application: VsdApplication) : Context = application


}

