package pt.isel.vsddashboardapplication.communication.provider

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import pt.isel.vsddashboardapplication.model.converters.*
import retrofit2.converter.moshi.MoshiConverterFactory

object MoshiProvider {

    fun get() = Moshi.Builder()
        .add(KotlinJsonAdapterFactory())
        .add(PortStatusConverter())
        .add(PortTypeConverter())
        .add(StatusConverter())
        .add(BootstrapStatusConverter())
        .add(CmdStatusConverter())
        .add(EntityScopeConverter())
        .add(FamilyConverter())
        .add(OperationalStateConverter())
        .add(PermittedActionConverter())
        .add(PersonalityConverter())
        .add(PortSpeedConverter())
        .add(ProbeTypeConverter())
        .add(RoleConverter())
        .add(SeverityConverter())
        .add(SystemTypeConverter())
        .add(EventConverter())
        .build()

    fun getFactory() = MoshiConverterFactory.create(get())
}