package pt.isel.vsddashboardapplication.injection.module

import android.content.Context
import android.content.Context.MODE_PRIVATE
import android.content.SharedPreferences
import dagger.Module
import dagger.Provides
import pt.isel.vsddashboardapplication.VsdApplication
import pt.isel.vsddashboardapplication.utils.SharedPreferenceKeys
import javax.inject.Singleton

@Module
class ApplicationModule(private val application: VsdApplication, private val context: Context? = null){

    @Provides
    @Singleton
    fun provideApplication() = application

    @Provides
    @Singleton
    fun providesContext() = context ?: application.baseContext

    @Provides
    @Singleton
    fun providesSharedPreferenes() : SharedPreferences =
        application.getSharedPreferences(SharedPreferenceKeys.BASE_SP, MODE_PRIVATE)

}