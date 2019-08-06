package pt.isel.vsddashboardapplication.communication.provider

import com.squareup.moshi.Moshi
import com.squareup.moshi.adapters.PolymorphicJsonAdapterFactory
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import pt.isel.vsddashboardapplication.model.*
import pt.isel.vsddashboardapplication.model.converters.BootstapStatusAdapter
import retrofit2.converter.moshi.MoshiConverterFactory

object MoshiProvider {

    fun get() = Moshi.Builder()
            .add(KotlinJsonAdapterFactory())
            .add(BootstapStatusAdapter())
            .add(PolymorphicJsonAdapterFactory.of(BaseEvent::class.java, "entityType")
                .withSubtype(Enterprise::class.java, "enterprise")
                .withSubtype(NSGateway::class.java, "nsgateway")
                .withSubtype(NSPort::class.java, "nsport")
                .withSubtype(VRS::class.java, "vrs")
                .withSubtype(VSC::class.java, "vsc")
                .withSubtype(VSP::class.java, "vsp"))
            .build()

    fun getFactory() = MoshiConverterFactory.create(get())
}