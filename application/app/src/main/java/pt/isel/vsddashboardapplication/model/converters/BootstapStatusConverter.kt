package pt.isel.vsddashboardapplication.model.converters

import androidx.room.TypeConverter
import pt.isel.vsddashboardapplication.model.enumerables.BootstrapStatus

class BootstapStatusConverter {

    @TypeConverter
    fun convertTo(value: BootstrapStatus) : String =
        value.toString()

    @TypeConverter
    fun convertFrom(value: String) : BootstrapStatus? =
        BootstrapStatus.values().find { it.name == value }

}