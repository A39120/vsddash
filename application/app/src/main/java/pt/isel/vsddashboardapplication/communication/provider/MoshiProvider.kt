package pt.isel.vsddashboardapplication.communication.provider

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import pt.isel.vsddashboardapplication.model.converters.BootstapStatusAdapter
import retrofit2.converter.moshi.MoshiConverterFactory

object MoshiProvider {

    fun get() = Moshi.Builder()
            .add(KotlinJsonAdapterFactory())
            .add(BootstapStatusAdapter())
            .build()

    fun getFactory() = MoshiConverterFactory.create(get())
}